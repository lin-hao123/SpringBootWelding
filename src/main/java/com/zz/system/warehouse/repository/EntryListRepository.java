package com.zz.system.warehouse.repository;

import com.zz.system.warehouse.entity.DeliveryList;
import com.zz.system.warehouse.entity.EntryList;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * created by linlx on 2020/1/20
 */
public interface EntryListRepository extends JpaRepository<EntryList,Long> {

    Page<EntryList> findByWeldingProductsLike(String weldingProducts, Pageable pageable);

    @Modifying
    @Query("update EntryList el set "+
            "el.weldingProducts=CASE WHEN:#{#m.weldingProducts} IS NULL THEN el.weldingProducts ELSE:#{#m.weldingProducts} END,"+
            "el.num=CASE WHEN:#{#m.num} IS NULL THEN el.num ELSE:#{#m.num} END,"+
            "el.warehouse=CASE WHEN:#{#m.warehouse} IS NULL THEN el.warehouse ELSE:#{#m.warehouse} END,"+
            "el.supplier=CASE WHEN:#{#m.supplier} IS NULL THEN el.supplier ELSE:#{#m.supplier} END,"+
            "el.entryTime=CASE WHEN:#{#m.entryTime} IS NULL THEN el.entryTime ELSE:#{#m.entryTime} END "+
            "where el.entryListId=:#{#m.entryListId}")
    public Integer updateEntryList(@Param("m") EntryList entryList);
}
