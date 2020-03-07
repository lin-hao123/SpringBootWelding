package com.zz.system.production.service;

import com.zz.system.production.entity.DispatchInformation;
import org.springframework.data.domain.Page;

/**
 * created by linlx on 2020/1/22
 */
public interface DispatchInformationService {

    Page<DispatchInformation> findByProductNameLike(String productName,int page,int size);

    DispatchInformation findById(Long dispatchId);

    DispatchInformation create(DispatchInformation dispatchInformation);

    void delete(Long dispatchId);
    
    Integer updateDispatchInformation(DispatchInformation dispatchInformation);
}
