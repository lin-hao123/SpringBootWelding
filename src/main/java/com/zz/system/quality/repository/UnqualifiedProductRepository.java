package com.zz.system.quality.repository;

import com.zz.system.quality.entity.MaterialInspection;
import com.zz.system.quality.entity.UnqualifiedProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/2/24
 */
public interface UnqualifiedProductRepository extends JpaRepository<UnqualifiedProduct,Long> {

    Page<UnqualifiedProduct> findByWeldingProductsLike(String weldingProducts, Pageable pageable);
    
    @Modifying
   	@Query("update UnqualifiedProduct up set "+
   	"up.productionOrder=CASE WHEN:#{#m.productionOrder} IS NULL THEN up.productionOrder ELSE:#{#m.productionOrder} END,"+
   	"up.discoveryTime=CASE WHEN:#{#m.discoveryTime} IS NULL THEN up.discoveryTime ELSE:#{#m.discoveryTime} END,"+
   	"up.disqualificationReason=CASE WHEN:#{#m.disqualificationReason} IS NULL THEN up.disqualificationReason ELSE:#{#m.disqualificationReason} END,"+
   	"up.weldingProducts=CASE WHEN:#{#m.weldingProducts} IS NULL THEN up.weldingProducts ELSE:#{#m.weldingProducts} END "+
   	"where up.unqualifiedProductId=:#{#m.unqualifiedProductId}")
   	public Integer updateUnqualifiedProduct(@Param("m") UnqualifiedProduct unqualifiedProduct);
}
