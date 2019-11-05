package com.joven.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.joven.base.entity.DailyExpensesInfoEntity;
import com.joven.base.entity.DailyExpensesInfoReq;
import com.joven.base.mapper.DailyExpensesInfoMapper;
import com.joven.base.service.DailyExpensesInfoService;
import com.joven.base.utils.BaseValidateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Slf4j
@Component
public class DailyExpensesInfoServiceImpl implements DailyExpensesInfoService {

    @Autowired
    DailyExpensesInfoMapper dailyExpensesInfoMapper;

    @Override
    public List<DailyExpensesInfoEntity> getAllInfos(Integer begin, Integer pageSize) {
	    Assert.isTrue(BaseValidateUtil.isNumericByAscill(begin.toString()),"begin is not Numeric");
	    Assert.isTrue(BaseValidateUtil.isNumericByAscill(pageSize.toString()),"pageSize is not Numeric");
	    return dailyExpensesInfoMapper.getAllInfos(begin,pageSize);
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
        dailyExpensesInfoMapper.getInfoByIds(ids);
        return null;
    }

    @Override
    public Boolean modifyInfoById(DailyExpensesInfoEntity targetInfo) {
        dailyExpensesInfoMapper.modifyInfoById(targetInfo);
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteInfoByIds(List<String> ids) {
        dailyExpensesInfoMapper.deleteInfoByIds(ids);
    }


}
