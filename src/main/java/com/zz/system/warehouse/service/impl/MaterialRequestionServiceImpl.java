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
    public Page<MaterialRequestion> findByMaterialIdLike(String materialId, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(materialId==null || materialId==""){
            materialId="%%";
        }else {
            materialId="%"+materialId+"%";
        }
        return materialRequestionRepository.findByMaterialIdLike(materialId,pageable);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long materialRequestionId) {
        materialRequestionRepository.deleteById(materialRequestionId);
    }

    @Override
    public MaterialRequestion create(MaterialRequestion materialRequestion) {
        materialRequestion.setCreateTime(new Date());
        materialRequestion.setFinishTime(new Date());
        return materialRequestionRepository.save(materialRequestion);
    }

    @Override
    public MaterialRequestion findByMaterialRequestionId(Long materialRequestionId) {
        return materialRequestionRepository.findById(materialRequestionId).get();
    }
}
