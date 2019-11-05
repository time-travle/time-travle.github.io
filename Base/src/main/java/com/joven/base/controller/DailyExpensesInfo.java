package com.joven.base.controller;


import com.joven.base.entity.DailyExpensesInfoEntity;
import com.joven.base.service.DailyExpensesInfoService;
import com.joven.base.utils.BaseValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.AssertTrue;
import java.util.ArrayList;
import java.util.List;

@RequestMapping(value = "/dailyExpensesInfo")
@RestController
public class DailyExpensesInfo {
    @Autowired
    DailyExpensesInfoService dailyExpensesInfoService;


    @RequestMapping(value = "/getInfoById/{id}", method = RequestMethod.POST, consumes = "application/json")
    public DailyExpensesInfoEntity getInfoById(@PathVariable String id) {
        return dailyExpensesInfoService.getInfoById(id);
    }

    @RequestMapping(value = "/getAll")
    public List<DailyExpensesInfoEntity> getAll(@RequestParam("beginNum") Integer beginNum,
                                                @RequestParam("pageSizeNum") Integer pageSizeNum) {
        List<DailyExpensesInfoEntity> allInfos = new ArrayList<DailyExpensesInfoEntity>();
        allInfos = dailyExpensesInfoService.getAllInfos(beginNum, pageSizeNum);
        return allInfos;
    }

    @RequestMapping(value = "/delete")
    public void deleteInfoByIds(List<String> ids) {
        //TODO
        dailyExpensesInfoService.deleteInfoByIds(ids);
    }

    @RequestMapping(value = "/delete/{id}")
    public void deleteInfoByIds(String id) {
        List<String> req = new ArrayList<String>();
        req.add(id);
        dailyExpensesInfoService.deleteInfoByIds(req);
    }

    @RequestMapping(value = "/modify")
    public String modifyInfo(DailyExpensesInfoEntity targetInfo) {
        //TODO
        return dailyExpensesInfoService.modifyInfoById(targetInfo).toString();
    }

    @RequestMapping(value = "/getInfos")
    public List<DailyExpensesInfoEntity> getInfoByIds(List<String> ids) {
        //TODO
        return dailyExpensesInfoService.getInfoByIds(ids);
    }
}
