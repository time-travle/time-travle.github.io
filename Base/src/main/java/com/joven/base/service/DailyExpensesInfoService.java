package com.joven.base.service;

import com.joven.base.entity.DailyExpensesInfoEntity;

import java.util.List;


public interface DailyExpensesInfoService {

    List<DailyExpensesInfoEntity> getAllInfos();

    DailyExpensesInfoEntity getInfoById(String id);

    List<DailyExpensesInfoEntity> getInfoByIds(List<String> ids);

    Boolean modifyInfoById(String id);


}
