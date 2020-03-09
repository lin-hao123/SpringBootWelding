package com.zz.system.quality.service;

import com.zz.system.quality.entity.Announcement;
import org.springframework.data.domain.Page;


/**
 * created by linlx on 2020/2/24
 */
public interface AnnouncementService {

    Page<Announcement> findByAnnouncementTitleLike(String announcementTitle, int page,int size);

    Announcement create(Announcement announcement);

    void delete(Long announcementId);

    Announcement findById(Long announcementId);

    Integer updateAnnouncement(Announcement announcement);
}
