package com.zz.system.warehouse.service;

import com.zz.system.warehouse.entity.EntryList;
import org.springframework.data.domain.Page;

import javax.swing.text.html.parser.Entity;

/**
 * created by linlx on 2020/1/20
 */
public interface EntryListService {

    Page<EntryList> findByWeldingProductsLike(String materialId,int page,int size);

    EntryList create(EntryList entryList);

    void delete(Long entryListId);

    EntryList findById(Long entryListId);

    Integer updateEntryList(EntryList entryList);
}
