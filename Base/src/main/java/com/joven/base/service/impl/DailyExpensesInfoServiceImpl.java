package com.joven.base.service.impl;

import com.joven.base.entity.DailyExpensesInfoEntity;
import com.joven.base.mapper.DailyExpensesInfoMapper;
import com.joven.base.service.DailyExpensesInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Component
public class DailyExpensesInfoServiceImpl implements DailyExpensesInfoService {

    @Autowired
    DailyExpensesInfoMapper dailyExpensesInfoMapper;

    @Override
    public List<DailyExpensesInfoEntity> getAllInfos() {
        return null;
    }

    @Override
    public DailyExpensesInfoEntity getInfoById(String id) {
        log.debug("DailyExpensesInfoServiceImpl--id-->",id);
        Assert.notNull(id,"id is null");
        DailyExpensesInfoEntity res = dailyExpensesInfoMapper.getInfoById(id);
        return res;
    }

    @Override
    public List<DailyExpensesInfoEntity> getInfoByIds(List<String> ids) {
        return null;
    }

    @Override
    public Boolean modifyInfoById(String id) {
        return null;
    }







}
