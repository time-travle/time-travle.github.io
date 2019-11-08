package com.joven.base.service;

import com.github.pagehelper.PageInfo;
import com.joven.base.entity.DailyExpensesInfoEntity;

import java.util.List;


public interface DailyExpensesInfoService {

    List<DailyExpensesInfoEntity> getAllInfos(Integer begin, Integer pageSize);

    DailyExpensesInfoEntity getInfoById(String id);

    List<DailyExpensesInfoEntity> getInfoByIds(List<String> ids);

    Boolean modifyInfoById(DailyExpensesInfoEntity targetInfo);

    void deleteInfoByIds(List<String> ids);
}
