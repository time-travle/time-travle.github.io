package com.joven.base.service;

import com.joven.base.entity.DailyExpensesInfoEntity;
import com.joven.base.entity.DailyExpensesInfoResp;
import com.joven.base.entity.ResponseBody;

import java.util.List;


public interface DailyExpensesInfoService {

    DailyExpensesInfoResp getAllInfos(Integer begin, Integer pageSize);

    DailyExpensesInfoEntity getInfoById(String id);

    DailyExpensesInfoResp getInfoByIds(List<String> ids);

    ResponseBody modifyInfoById(DailyExpensesInfoEntity targetInfo);

    ResponseBody deleteInfoByIds(List<String> ids);

    ResponseBody bathModifyInfoById (List<DailyExpensesInfoEntity> lists);

    ResponseBody bathInsertInfos(List<DailyExpensesInfoEntity> lists);
}
