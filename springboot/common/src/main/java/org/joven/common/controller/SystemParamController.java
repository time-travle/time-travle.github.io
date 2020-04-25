/**
 * Project Name: blog project
 * File Name: SystemController
 * Package Name: org.joven.common.controller
 * Date: 2020/1/5 2:13
 * Copyright (c) 2020,All Rights Reserved.
 */
package org.joven.common.controller;

import org.joven.base.entity.ResponseBody;
import org.joven.common.entity.SystemParamEntity;
import org.joven.common.entity.SystemParamViewEntity;
import org.joven.common.service.SystemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * CreateBy Notebook
 * Date: 2020/1/5 2:13
 * Version: 1.0
 * Remark: 获取系统参数的一个服务
 */
@RestController
@RequestMapping(value = "/common/system")
public class SystemParamController {
	/*
	使用构造器注入（常量注入不提倡）必须依赖（主要）
	 */
	private SystemParamService systemParamService;

	@Autowired
	public SystemParamController(SystemParamService systemParamService) {
		this.systemParamService = systemParamService;
	}

	
	@RequestMapping(value = "/getSystemParamValueWithDefault")
	public String getSystemParamValue(String systemParamId, String defaultValue) {
		return systemParamService.getSystemParamValue(systemParamId, defaultValue);
	}
	@RequestMapping(value = "/getSystemParamValue")
	public String getSystemParamValue(String systemParamId) {
		return systemParamService.getSystemParamValue(systemParamId);
	}
	@RequestMapping(value = "/getSystemParamInfo")
	public SystemParamViewEntity getSystemParamInfo(String systemParamId) {
		return systemParamService.getSystemParamInfo(systemParamId);
	}

	@RequestMapping(value = "/getSystemParamInfos")
	public List<SystemParamEntity> getSystemParamInfos(List<String> systemParamIds) {
		return systemParamService.getSystemParamInfos(systemParamIds);
	}

	@RequestMapping(value = "/updateSystemParamInfo")
	public ResponseBody updateSystemParamInfo(SystemParamEntity systemParamEntity) {
		return systemParamService.updateSystemParamInfo(systemParamEntity);
	}

	@RequestMapping(value = "/deleteSystemParamInfo")
	public ResponseBody deleteSystemParamInfo(String systemParamId) {
		return systemParamService.deleteSystemParamInfo(systemParamId);
	}

	@RequestMapping(value = "/addSystemParam2DB")
	public ResponseBody addSystemParam2DB(SystemParamEntity systemParamEntity) {
		return systemParamService.addSystemParam2DB(systemParamEntity);
	}
}
