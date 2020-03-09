package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.EntryList;
import com.zz.system.warehouse.entity.Material;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/20
 */
public interface MaterialRepository extends JpaRepository<Material,Long> {

    Page<Material> findByMaterialNameLike(String materialName, Pageable pageable);

    @Modifying
    @Query("update Material ml set "+
            "ml.materialName=CASE WHEN:#{#m.materialName} IS NULL THEN ml.materialName ELSE:#{#m.materialName} END,"+
            "ml.materialSize=CASE WHEN:#{#m.materialSize} IS NULL THEN ml.materialSize ELSE:#{#m.materialSize} END,"+
            "ml.warehouse=CASE WHEN:#{#m.warehouse} IS NULL THEN ml.warehouse ELSE:#{#m.warehouse} END,"+
            "ml.location=CASE WHEN:#{#m.location} IS NULL THEN ml.location ELSE:#{#m.location} END,"+
            "ml.leftNum=CASE WHEN:#{#m.leftNum} IS NULL THEN ml.leftNum ELSE:#{#m.leftNum} END,"+
            "ml.supplier=CASE WHEN:#{#m.supplier} IS NULL THEN ml.supplier ELSE:#{#m.supplier} END "+
            "where ml.materialId=:#{#m.materialId}")
    public Integer updateMaterial(@Param("m") Material material);

    Material findByMaterialId(String materialId);

    void deleteByMaterialId(String materialId);


}
