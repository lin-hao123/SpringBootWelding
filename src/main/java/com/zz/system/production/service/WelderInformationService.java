package com.zz.system.production.service;

import com.zz.system.production.entity.WelderInformation;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/21
 */
public interface WelderInformationService {

    Page<WelderInformation> findByWelderNameLike(String welderName,int page,int size);

    WelderInformation findById(Long welderId);

    WelderInformation create(WelderInformation welderInformation);

    void delete(Long welderId);
}
