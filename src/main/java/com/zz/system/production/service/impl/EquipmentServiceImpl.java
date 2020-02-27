package com.zz.system.production.service.impl;

import com.zz.system.production.entity.Equipment;
import com.zz.system.production.repository.EquipmentRepository;
import com.zz.system.production.service.EquipmentService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * created by linlx on 2020/2/25
 */
@Service
public class EquipmentServiceImpl implements EquipmentService {

    @Resource
    EquipmentRepository equipmentRepository;


    @Override
    public Page<Equipment> findByEquipmentNameLike(String equipmentName, int page, int size) {

        Sort sort=new Sort(Sort.Direction.DESC,"useStatus");
        Pageable pageable= PageRequest.of(page,size,sort);
        if (equipmentName==null||equipmentName==""){
            equipmentName= "%%";
        }else {
            equipmentName= "%"+equipmentName+"%";
        }
        return equipmentRepository.findByEquipmentNameLike(equipmentName,pageable);
    }

    @Override
    public Equipment create(Equipment equipment) {
        return equipmentRepository.save(equipment);
    }

    @Override
    public void delete(Long equipmentId) {

        equipmentRepository.deleteById(equipmentId);
    }

    @Override
    public Equipment findById(Long equipmentId) {
        return equipmentRepository.findById(equipmentId).get();
    }

}
