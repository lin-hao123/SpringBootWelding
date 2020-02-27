package com.zz.system.quality.repository;

import com.zz.system.quality.entity.UnqualifiedProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by linlx on 2020/2/24
 */
public interface UnqualifiedProductRepository extends JpaRepository<UnqualifiedProduct,Long> {

    Page<UnqualifiedProduct> findByWeldingProductsLike(String weldingProducts, Pageable pageable);
}
