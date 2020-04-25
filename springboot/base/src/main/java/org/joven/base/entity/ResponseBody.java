/**
 * Project Name: blog project
 * File Name: ResponseBody
 * Package Name: org.joven.base.entity
 * Date: 2019/11/5 22:29
 * Copyright (c) 2019,All Rights Reserved.
 */
package org.joven.base.entity;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * CreateBy Notebook
 * Date: 2019/11/5 22:29
 * Version:
 * Remark: 前端请求后端时的一个返回值  使用注解的方式 省略set/get
 */
@Data
@AllArgsConstructor
public class ResponseBody {
	private String returnCode;
	private String returnMessage;

	public ResponseBody() {
		this.returnCode = "0";
		this.returnMessage = "success";
	}
}
