package com.zz.system.production.repository;

import com.zz.system.design.entity.TaskInstruction;
import com.zz.system.production.entity.WelderInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/21
 */
public interface WelderInformationRepository extends JpaRepository<WelderInformation,Long> {

    Page<WelderInformation> findByWelderNameLike(String welderName, Pageable pageable);
    
    @Modifying
   	@Query("update WelderInformation wi set "+
   	"wi.welderName=CASE WHEN:#{#m.welderName} IS NULL THEN wi.welderName ELSE:#{#m.welderName} END,"+
   	"wi.welderTel=CASE WHEN:#{#m.welderTel} IS NULL THEN wi.welderTel ELSE:#{#m.welderTel} END,"+
   	"wi.sex=CASE WHEN:#{#m.sex} IS NULL THEN wi.sex ELSE:#{#m.sex} END,"+
   	"wi.birthday=CASE WHEN:#{#m.birthday} IS NULL THEN wi.birthday ELSE:#{#m.birthday} END,"+
   	"wi.address=CASE WHEN:#{#m.address} IS NULL THEN wi.address ELSE:#{#m.address} END,"+
   	"wi.trainingInformation=CASE WHEN:#{#m.trainingInformation} IS NULL THEN wi.trainingInformation ELSE:#{#m.trainingInformation} END,"+
   	"wi.createTime=CASE WHEN:#{#m.createTime} IS NULL THEN wi.createTime ELSE:#{#m.createTime} END,"+
   	"wi.updateTime=CASE WHEN:#{#m.updateTime} IS NULL THEN wi.updateTime ELSE:#{#m.updateTime} END "+
   	"where wi.welderId=:#{#m.welderId}")
   	public Integer updateWelderInformation(@Param("m") WelderInformation WelderInformation); 
}
