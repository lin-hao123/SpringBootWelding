package com.zz.system.warehouse.service;

import com.zz.system.warehouse.entity.Material;
import org.springframework.data.domain.Page;

import java.rmi.MarshalException;

/**
 * created by linlx on 2020/1/20
 */
public interface MaterialService {

    Page<Material> findByMaterialNameLike(String materialName,int page,int size);

    Material create(Material material);

    void delete(String materialId);

    Material findById(String materialId);

    Integer updateMaterial(Material material);
}
