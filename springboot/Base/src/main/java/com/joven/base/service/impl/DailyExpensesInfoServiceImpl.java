package com.joven.base.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.joven.base.entity.DailyExpensesInfoEntity;
import com.joven.base.entity.DailyExpensesInfoResp;
import com.joven.base.entity.ResponseBody;
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
    public DailyExpensesInfoResp getAllInfos(Integer begin, Integer pageSize) {
	    log.debug("getAllInfos --->req:begin:",begin,"  pageSize:",pageSize);
        // 用来分页查询的入参不能为空
        Assert.isTrue(BaseValidateUtil.isNumericByAscill(begin.toString()),"begin is not Numeric");
	    Assert.isTrue(BaseValidateUtil.isNumericByAscill(pageSize.toString()),"pageSize is not Numeric");

	    DailyExpensesInfoResp resp = new DailyExpensesInfoResp();
        ResponseBody responseBody = new ResponseBody("0","success","");

        try{
            List<DailyExpensesInfoEntity> infos = dailyExpensesInfoMapper.getAllInfos(begin, pageSize);
            resp.setInfos(infos);
        }catch (Exception e){
            responseBody.setReturnCode("-1");
            responseBody.setSuccessMessage("");
            responseBody.setErrorMessage("查询失败");
        }

        resp.setResponseBody(responseBody);

	    return resp;
    }

    @Override
    public DailyExpensesInfoEntity getInfoById(String id) {
        log.debug("DailyExpensesInfoServiceImpl--id-->",id);
        Assert.notNull(id,"id is null");
        DailyExpensesInfoEntity res = dailyExpensesInfoMapper.getInfoById(id);
        return res;
    }

    @Override
    public DailyExpensesInfoResp getInfoByIds(List<String> ids) {
        log.debug("getInfoByIds req -->", JSONObject.toJSON(ids));

        dailyExpensesInfoMapper.getInfoByIds(ids);

        return new DailyExpensesInfoResp();

    }

    @Override
    public ResponseBody modifyInfoById(DailyExpensesInfoEntity targetInfo) {
        dailyExpensesInfoMapper.modifyInfoById(targetInfo);
        return new ResponseBody("0","success","");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseBody deleteInfoByIds(List<String> ids) {
        dailyExpensesInfoMapper.deleteInfoByIds(ids);
        return  new ResponseBody("0","success","");
    }

    @Override
    public ResponseBody bathModifyInfoById(List<DailyExpensesInfoEntity> lists) {
        dailyExpensesInfoMapper.bathModifyInfoById(lists);
        return new ResponseBody("0","success","");
    }

    @Override
    public ResponseBody bathInsertInfos(List<DailyExpensesInfoEntity> lists) {
        dailyExpensesInfoMapper.bathInsertInfos(lists);
        return new ResponseBody("0","success","");
    }


}
