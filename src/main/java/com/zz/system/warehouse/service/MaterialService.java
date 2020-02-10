package com.zz.system.warehouse.service;

import com.zz.system.warehouse.entity.Material;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/20
 */
public interface MaterialService {

    Page<Material> findByMaterialNameLike(String materialName,int page,int size);

    Material create(Material material);

    void delete(Long materialId);

    Material findById(Long materialId);
}
