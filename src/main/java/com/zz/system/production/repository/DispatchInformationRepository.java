package com.zz.system.production.repository;

import com.zz.system.production.entity.DispatchInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by linlx on 2020/1/22
 */
public interface DispatchInformationRepository extends JpaRepository<DispatchInformation,Long> {

    Page<DispatchInformation> findByProductNameLike(String productName, Pageable pageable);
}
