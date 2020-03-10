package com.zz.system.warehouse.service.impl;

import com.zz.system.warehouse.entity.MaterialRequestion;
import com.zz.system.warehouse.repository.MaterialRequestionRepository;
import com.zz.system.warehouse.service.MaterialRequestionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * created by linlx on 2020/1/15
 */
@Service
public class MaterialRequestionServiceImpl implements MaterialRequestionService {

    @Resource
    MaterialRequestionRepository materialRequestionRepository;

    @Override
    public Page<MaterialRequestion> findByUserLike(String user, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(user==null || user==""){
            user="%%";
        }else {
            user="%"+user+"%";
        }
        return materialRequestionRepository.findByUserLike(user,pageable);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long materialRequestionId) {
        materialRequestionRepository.deleteById(materialRequestionId);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public MaterialRequestion create(MaterialRequestion materialRequestion) {
        materialRequestion.setCreateTime(new Date());
        return materialRequestionRepository.save(materialRequestion);
    }

    @Override
    public MaterialRequestion findByMaterialRequestionId(Long materialRequestionId) {
        return materialRequestionRepository.findById(materialRequestionId).get();
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public Integer updateMaterialRequestion(MaterialRequestion materialRequestion) {
        return materialRequestionRepository.updateMaterialRequestion(materialRequestion);
    }
}
