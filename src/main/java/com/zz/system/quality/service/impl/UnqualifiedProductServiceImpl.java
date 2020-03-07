package com.zz.system.quality.service.impl;

import com.zz.system.quality.entity.UnqualifiedProduct;
import com.zz.system.quality.repository.UnqualifiedProductRepository;
import com.zz.system.quality.service.UnqualifiedProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * created by linlx on 2020/2/24
 */

@Service
public class UnqualifiedProductServiceImpl implements UnqualifiedProductService {

    @Resource
    UnqualifiedProductRepository unqualifiedProductRepository;


    @Override
    public Page<UnqualifiedProduct> findByWeldingProductsLike(String weldingProducts, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"discoveryTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(weldingProducts==null||weldingProducts==""){
            weldingProducts="%%";
        }else {
            weldingProducts="%"+weldingProducts+"%";
        }
        return unqualifiedProductRepository.findByWeldingProductsLike(weldingProducts,pageable);
    }

    @Override
    public UnqualifiedProduct create(UnqualifiedProduct unqualifiedProduct) {
        unqualifiedProduct.setDiscoveryTime(new Date());
        return unqualifiedProductRepository.save(unqualifiedProduct);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long unqualifiedProductId) {
        unqualifiedProductRepository.deleteById(unqualifiedProductId);

    }

    @Override
    public UnqualifiedProduct findById(Long unqualifiedProductId) {
        return unqualifiedProductRepository.findById(unqualifiedProductId).get();
    }

	@Override
	@Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
	public Integer updateUnqualifiedProduct(UnqualifiedProduct unqualifiedProduct) {
		return unqualifiedProductRepository.updateUnqualifiedProduct(unqualifiedProduct);
	}
}
