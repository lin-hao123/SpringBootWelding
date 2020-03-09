package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.Material;
import com.zz.system.warehouse.entity.MaterialRequestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/15
 */
public interface MaterialRequestionRepository extends JpaRepository<MaterialRequestion,Long> {

    Page<MaterialRequestion> findByUserLike(String user, Pageable pageable);

    @Modifying
    @Query("update MaterialRequestion mr set "+
            "mr.department=CASE WHEN:#{#m.department} IS NULL THEN mr.department ELSE:#{#m.department} END,"+
            "mr.user=CASE WHEN:#{#m.user} IS NULL THEN mr.user ELSE:#{#m.user} END,"+
            "mr.material=CASE WHEN:#{#m.material} IS NULL THEN mr.material ELSE:#{#m.material} END,"+
            "mr.createTime=CASE WHEN:#{#m.createTime} IS NULL THEN mr.createTime ELSE:#{#m.createTime} END,"+
            "mr.finished=CASE WHEN:#{#m.finished} IS NULL THEN mr.finished ELSE:#{#m.finished} END,"+
            "mr.materialNote=CASE WHEN:#{#m.materialNote} IS NULL THEN mr.materialNote ELSE:#{#m.materialNote} END "+
            "where mr.materialRequestionId=:#{#m.materialRequestionId}")
    public Integer updateMaterialRequestion(@Param("m") MaterialRequestion materialRequestion);

}
