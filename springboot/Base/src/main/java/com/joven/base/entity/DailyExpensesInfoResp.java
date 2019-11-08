/**
 * Project Name: ershuaizhang.github.io
 * File Name: DailyExpensesInfoResp
 * Package Name: com.joven.base.entity
 * Date: 2019/11/5 23:14
 * Copyright (c) 2019,All Rights Reserved.
 */
package com.joven.base.entity;

import com.github.pagehelper.PageHelper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * CreateBy Notebook
 * Date: 2019/11/5 23:14
 * Version: 
 * Remark: 
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyExpensesInfoResp {
    private List<DailyExpensesInfoEntity> infos;
    private ResponseBody responseBody;
    private PageHelper pageInfo;
}
