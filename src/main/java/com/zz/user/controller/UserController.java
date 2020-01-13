
package com.zz.user.controller;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zz.exception.DataValidationException;
import com.zz.redis.service.RedisTemplateService;
import com.zz.user.dto.BasicUserDto;
import com.zz.user.dto.RoleListDto;
import com.zz.user.dto.UserDto;
import com.zz.user.entity.Permission;
import com.zz.user.entity.Role;
import com.zz.user.entity.User;
import com.zz.user.form.BasicUserForm;
import com.zz.user.form.CreateUserForm;
import com.zz.user.form.UpdateUserForm;
import com.zz.user.service.UserService;
import com.zz.util.FormConvertDto;
import com.zz.util.JwtUtil;
import com.zz.util.KeyUtil;
import com.zz.util.MD5Utils;
import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年10月28日 下午7:58:15 
 */
@Api(value = "用户Controller")
@RestController
@RequestMapping("user")
public class UserController {
	@Resource
	UserService userService;
	
	@Resource
	RedisTemplateService redisTemplateService;

	@ApiOperation(value = "创建用户基本信息")
	@ApiImplicitParam(name = "basicUserForm", value = "用户实体", required = true, dataType = "BasicUserForm")
	@PostMapping("basicUserForm")
	public ResponseData save(@Valid @RequestBody BasicUserForm basicUserForm,BindingResult bindingResult){
		System.out.println("用户名："+basicUserForm.getName());
		if(bindingResult.hasErrors()){
			throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
		}
		BasicUserDto basicUserDto=FormConvertDto.BasicUserConvert(basicUserForm);
		User user=new User();
		BeanUtils.copyProperties(basicUserDto, user);	
		user.setId(KeyUtil.genUniqueKey());	
		String enpwd=MD5Utils.encrypt(user.getName(), user.getPwd());
		user.setPwd(enpwd);
	    User rs=userService.save(user);
	    if(rs!=null){
	    	return ResponseDataUtil.success("创建成功", rs);
	    }else{
	    	return ResponseDataUtil.failure("创建失败");
	    }
	    
	}
	
	@ApiOperation(value = "新建用户信息")
	@ApiImplicitParam(name = "createUserForm", value = "新建用户实体", required = true, dataType = "CreateUserForm")
	@PostMapping("createUserForm")
	public ResponseData save(@Valid @RequestBody CreateUserForm createUserForm,BindingResult bindingResult ){
		if(bindingResult.hasErrors()){
			throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
		}
		boolean flag1=false;
		boolean flag2=false;
		User user=new User();
		UserDto userDto=FormConvertDto.createUserDtoConvert(createUserForm);
		BeanUtils.copyProperties(userDto,user);
		User rs1= userService.createUser(user);
		if(rs1!=null){
			flag1=true;
		}
		RoleListDto roleListDto=FormConvertDto.createRoleListDtoConvert(createUserForm);
		int sum=0;
		for(int i=0;i<roleListDto.getRoleList().size();i++){
			Integer rs2=userService.add(user.getId(), roleListDto.getRoleList().get(i).getId());
			sum++;
		}
		if(sum==roleListDto.getRoleList().size()){
			flag2=true;
		}
		if(flag1&&flag2){
			return ResponseDataUtil.success("创建成功", createUserForm);
		}else{
			return ResponseDataUtil.failure("创建失败");
		}
	}
	
	
	@ApiOperation(value = "查找用户是否存在")
	@ApiImplicitParam(name = "name", value = "用户名字", required = true, dataType = "String", paramType="path")
	@GetMapping("checkName/{name}")
	public User findByName(@PathVariable("name") String name){
		return userService.findByName(name);
	}
	
	@ApiOperation(value = "用户登录")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "name", value = "用户名", required = true, dataType = "String", paramType = "path"),
	@ApiImplicitParam(name = "pwd", value = "密码", required = true, dataType = "String", paramType = "path")
	})
	@PostMapping("login")
	public ResponseData findByNameAndPwd(@RequestParam("name") String name, @RequestParam("pwd") String pwd, @RequestParam("rememberMe") Boolean rememberMe){
		String enpwd = MD5Utils.encrypt(name, pwd);
//		Map<String, Object> params = new HashMap<>();
//		params.put("name", name);
//		params.put("pwd", enpwd);
		User user=userService.findByNameAndPwd(name, enpwd);
		UsernamePasswordToken token = new UsernamePasswordToken(name, enpwd, rememberMe);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			String token1=JwtUtil.sign(user.getName(), user.getPwd());
			redisTemplateService.set(token1, user);
			return ResponseDataUtil.success(token1,user);
		} catch (UnknownAccountException e) {
			return ResponseDataUtil.failure(e.getMessage());
		} catch (IncorrectCredentialsException e) {
			return ResponseDataUtil.failure(e.getMessage());
		} catch (LockedAccountException e) {
			return ResponseDataUtil.failure(e.getMessage());
		} catch (AuthenticationException e) {
			return ResponseDataUtil.failure("认证失败！");
		}
	}
	@PostMapping("/getlogin")
	@ResponseBody
	public User getLoginUser(){
		return (User) SecurityUtils.getSubject().getPrincipal();
	}	
	@ApiOperation(value = "查询用户是否拥有此权限")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "url", value = "权限地址", required = true, dataType = "String", paramType = "path")
		})
	@GetMapping("checkPermission/{token}/{url}")
	public Map checkPermission(@PathVariable("token") String token,@PathVariable("url") String url){
		String userName=JwtUtil.getUsername(token);
		User user=userService.findByName(userName);
		Map map=new HashMap();
		for(Role role:user.getRoles()){
			for(Permission permission:role.getPermissions()){
				if(permission.getUrl()==url){
					map.put("1", user);
				}else{
					map.put("0", "用户无此权限");
				}
			}
		}
		return map;
	}
	
	@ApiOperation(value = "根据token查询用户")
	@ApiImplicitParam(name = "token", value = "token", required = true, dataType = "String", paramType = "path")	
	@GetMapping("findByToken/{token}")
	public User findByToken(@PathVariable("token") String token){
		String userName=JwtUtil.getUsername(token);
		return userService.findByName(userName);
	}
	
	@ApiOperation(value = "根据用户id查找用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType="path")
	@GetMapping("findById/{id}")
	public User findById(@PathVariable("id") String id){
		return userService.findById(id);
	}
	
	@ApiOperation(value = "查看用户信息", notes = "查看所有用户所有信息")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", value = "开始页0开始", required = true, dataType = "int", paramType = "path"),
		@ApiImplicitParam(name = "size", value = "每页显示数量", required = true, dataType = "int", paramType = "path")
		})
	@GetMapping("showall/{page}/{size}")
	public Page<User> showAll(@PathVariable("page") int page,@PathVariable("size") int size){
		return userService.showall(page, size);
	}
	
	@ApiOperation(value = "删除用户", notes = "根据id删除用户")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String",paramType="path")
	@DeleteMapping("remove/{id}")
	public void deleteById(@PathVariable("id") String id){
		userService.deleteById(id);
	}
	
    @ApiOperation(value = "更新用户", notes = "根据用户id更新用户基本信息")
    @ApiImplicitParam(name = "updateUserForm", value = "基本信息form", required = true, dataType = "UpdateUserForm")
    @PutMapping("update")
    public ResponseData updateRole(@Valid @RequestBody UpdateUserForm updateUserForm,BindingResult bindingResult) {
    	if(bindingResult.hasErrors()){ 
    		throw new DataValidationException("验证错误");
//			throw new DataValidationException(bindingResult.getFieldError().getDefaultMessage());
		}
    	System.out.println("用户更新"+updateUserForm.getName());
    	UserDto userDto=FormConvertDto.userDtoConvert(updateUserForm);
    	RoleListDto roleListDto=FormConvertDto.roleListDtoConvert(updateUserForm);
    	User user=new User();
    	BeanUtils.copyProperties(userDto, user);
    	boolean flag1=false;
    	boolean flag2=false;
    	Integer rs=userService.update(user);
    	userService.delete(user.getId());
    	if(rs==1){
    		flag1=true;
   	    }
        List<User> users=userService.checkId(user.getId());
    	int sum=0;    	
    	if(users.size()==0){
    	for(int i=0;i<roleListDto.getRoleList().size();i++){
    		Integer rs2=userService.add(user.getId(), roleListDto.getRoleList().get(i).getId());
    		sum=sum+rs2;
    	  };
    	}
    	if(roleListDto.getRoleList().size()==sum){
    		flag2=true;
    	}
    	if(flag1 && flag2){
    		 return ResponseDataUtil.success("修改成功");
    	}else{
    		return ResponseDataUtil.failure(500, "修改失败");
    	}
    }
    
	@ApiOperation(value = "增加用户角色中间表") 
	@ApiImplicitParams({
		@ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "String", paramType = "path"),
		@ApiImplicitParam(name = "rid", value = "角色id", required = true, dataType = "String", paramType = "path")
		})
	@PostMapping("adduserrole/{uid}/{rid}")
	public ResponseData save(@PathVariable("uid") String u_id,@PathVariable("rid") String r_id){
	    return ResponseDataUtil.success("添加成功", userService.add(u_id, r_id));
	}
	
	@ApiOperation(value = "删除用户角色中间表", notes = "根据用户id删除")
	@ApiImplicitParam(name = "uid", value = "用户id", required = true, dataType = "String", paramType = "path")	
	@DeleteMapping("delete/{uid}")
	public Integer delete(@PathVariable("uid") String u_id){
		return userService.delete(u_id);
	}
	
	@ApiOperation(value = "根据用户id查找用户角色中间表")
	@ApiImplicitParam(name = "id", value = "用户id", required = true, dataType = "String", paramType="path")
	@GetMapping("checkId/{id}")
	public List<User> checkId(@PathVariable("id") String id){
		return userService.checkId(id);
	}
	
	@ApiOperation(value = "登录用户是否是超级管理员")
	@GetMapping("isAdmin")
	public ResponseData isAdmin(HttpServletRequest request){
		System.out.println("token:"+request.getHeader("TOKEN"));
		User loginUser= redisTemplateService.get(request.getHeader("TOKEN"),User.class);
  		String isAdmin="N";
		for(Role role:loginUser.getRoles()){
			if("超级管理员".equals(role.getDetail())){
				isAdmin="Y";
			}
		}
		return  ResponseDataUtil.success(isAdmin);
	}
	@ApiOperation(value = "用户个人信息")
	@GetMapping("personalMessage")
	public ResponseData personalMessage(HttpServletRequest request){
		User loginUser=redisTemplateService.get(request.getHeader("TOKEN"), User.class);
		if(loginUser!=null){
			return ResponseDataUtil.success("个人信息", loginUser);
		}else{
			return ResponseDataUtil.failure("请登录");
		}
	}
}
