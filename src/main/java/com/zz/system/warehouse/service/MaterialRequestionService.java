package com.zz.system.warehouse.service;

import com.zz.system.warehouse.entity.MaterialRequestion;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/15
 */
public interface MaterialRequestionService {

    Page<MaterialRequestion> findByMaterialIdLike(String materialId,int page,int size);

    void delete(Long materialRequestionId);

    MaterialRequestion create(MaterialRequestion materialRequestion);

    MaterialRequestion findByMaterialRequestionId(Long materialRequestionId);
}
