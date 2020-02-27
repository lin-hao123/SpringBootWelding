package com.zz.system.quality.service;

import com.zz.system.quality.entity.UnqualifiedProduct;
import org.springframework.data.domain.Page;


/**
 * created by linlx on 2020/2/24
 */
public interface UnqualifiedProductService {

    Page<UnqualifiedProduct> findByWeldingProductsLike(String weldingProducts, int page,int size);

    UnqualifiedProduct create(UnqualifiedProduct unqualifiedProduct);

    void delete(Long unqualifiedProductId);

    UnqualifiedProduct findById(Long unqualifiedProductId);

}
