package com.zz.system.quality.repository;

import com.zz.system.device.entity.DeviceMaintenance;
import com.zz.system.quality.entity.Announcement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/2/24
 */
public interface AnnouncementRepository extends JpaRepository<Announcement,Long> {

    Page<Announcement> findByAnnouncementTitleLike(String announcementTitle, Pageable pageable);

    @Modifying
    @Query("update Announcement a set "+
            "a.announcementTitle=CASE WHEN:#{#m.announcementTitle} IS NULL THEN a.announcementTitle ELSE:#{#m.announcementTitle} END,"+
            "a.announcementContent=CASE WHEN:#{#m.announcementContent} IS NULL THEN a.announcementContent ELSE:#{#m.announcementContent} END,"+
            "a.department=CASE WHEN:#{#m.department} IS NULL THEN a.department ELSE:#{#m.department} END,"+
            "a.createTime=CASE WHEN:#{#m.createTime} IS NULL THEN a.createTime ELSE:#{#m.createTime} END "+
            "where a.announcementId=:#{#m.announcementId}")
    Integer updateAnnouncement(@Param("m") Announcement announcement);
}
