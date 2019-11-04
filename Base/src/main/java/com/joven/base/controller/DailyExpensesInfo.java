package com.joven.base.controller;


import com.joven.base.entity.DailyExpensesInfoEntity;
import com.joven.base.service.DailyExpensesInfoService;
import com.joven.base.service.impl.DailyExpensesInfoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping(value = "/dailyExpensesInfo")
@RestController
public class DailyExpensesInfo {
    @Autowired
    DailyExpensesInfoService dailyExpensesInfoService;
    @Autowired
    DailyExpensesInfoServiceImpl dailyExpensesInfoServiceImpl;

    @RequestMapping(value = "/getInfoById/{id}")
    public DailyExpensesInfoEntity getInfoById(@PathVariable String id) {
        return dailyExpensesInfoService.getInfoById(id);
    }
    @RequestMapping(value = "/getAll")
    public List<DailyExpensesInfoEntity> getAll() {
        return dailyExpensesInfoService.getAllInfos();
    }
}
