package com.zz.system.warehouse.service.impl;

import com.zz.system.warehouse.entity.Material;
import com.zz.system.warehouse.repository.MaterialRepository;
import com.zz.system.warehouse.service.MaterialService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * created by linlx on 2020/1/20
 */
@Service
public class MaterialServiceImpl implements MaterialService {

    @Resource
    MaterialRepository materialRepository;


    @Override
    public Page<Material> findByMaterialNameLike(String materialName, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"materialId");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(materialName==null || materialName==""){
            materialName="%%";
        }else {
            materialName="%"+materialName+"%";
        }
        return materialRepository.findByMaterialNameLike(materialName,pageable);
    }

    @Override
    public Material create(Material material) {
        return materialRepository.save(material);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long materialId) {
        materialRepository.deleteById(materialId);
    }

    @Override
    public Material findById(Long materialId) {
        return materialRepository.findById(materialId).get();
    }
}
