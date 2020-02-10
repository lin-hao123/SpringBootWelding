package com.zz.system.production.repository;

import com.zz.system.production.entity.WelderInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * created by linlx on 2020/1/21
 */
public interface WelderInformationRepository extends JpaRepository<WelderInformation,Long> {

    Page<WelderInformation> findByWelderNameLike(String welderName, Pageable pageable);
}
