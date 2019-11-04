package com.joven.base.mapper;

import com.joven.base.entity.DailyExpensesInfoEntity;

import java.util.List;


public interface DailyExpensesInfoMapper {

    List<DailyExpensesInfoEntity> getAllInfos();

    DailyExpensesInfoEntity getInfoById(String id);

    List<DailyExpensesInfoEntity> getInfoByIds(List<String> ids);

    Boolean modifyInfoById(String id);

}
