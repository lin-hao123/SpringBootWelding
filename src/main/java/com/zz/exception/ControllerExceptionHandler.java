
package com.zz.exception;

import javax.persistence.NonUniqueResultException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.zz.vo.ResponseData;
import com.zz.vo.ResponseDataUtil;

/**
 * @Description: 
 * @Author asuslh
 * @DateTime 2019年10月13日 下午10:17:53 
 */
@ControllerAdvice
public class ControllerExceptionHandler {

	@ExceptionHandler(NonUniqueResultException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData handleNonUniqueResultException(NonUniqueResultException e){
		return ResponseDataUtil.failure(500, e.getMessage());
	}

	
	@ExceptionHandler(DataValidationException.class)
	@ResponseBody
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseData handDataValidationException(DataValidationException e){
		return ResponseDataUtil.failure(500, "输入数据错误："+e.getMessage());
		
	}

}
