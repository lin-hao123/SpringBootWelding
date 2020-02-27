package com.zz.system.quality.service.impl;

import com.zz.system.quality.entity.Announcement;
import com.zz.system.quality.repository.AnnouncementRepository;
import com.zz.system.quality.service.AnnouncementService;
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
public class AnnouncementServiceImpl implements AnnouncementService {

    @Resource
    AnnouncementRepository announcementRepository;


    @Override
    public Page<Announcement> findByAnnouncementContentLike(String announcementContent, int page, int size) {
        Sort sort=new Sort(Sort.Direction.DESC,"createTime");
        Pageable pageable= PageRequest.of(page,size,sort);
        if(announcementContent==null||announcementContent==""){
            announcementContent="%%";
        }else {
            announcementContent="%"+announcementContent+"%";
        }
        return announcementRepository.findByAnnouncementContentLike(announcementContent,pageable);
    }

    @Override
    public Announcement create(Announcement announcement) {
        announcement.setCreateTime(new Date());
        announcement.setUpdateTime(new Date());
        return announcementRepository.save(announcement);
    }

    @Override
    @Transactional(value = "transactionManager", rollbackFor = {Exception.class}, readOnly = false)
    public void delete(Long announcementId) {
        announcementRepository.deleteById(announcementId);

    }

    @Override
    public Announcement findById(Long announcementId) {
        return announcementRepository.findById(announcementId).get();
    }
}
