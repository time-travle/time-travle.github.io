/**
 * Project Name: time-travle.github.io
 * File Name: MvcConfigCustomer
 * Package Name: org.joven.common.config
 * Date: 2020/5/4 21:21
 * Copyright (c) 2020,All Rights Reserved.
 */
package org.joven.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * CreateBy Administrator
 * Date: 2020/5/4 21:21
 * Version:
 * Remark:
 */
@Configuration
public class WebMvcConfigCustomer extends WebMvcConfigurationSupport {

    /**
     * fixed question: No mapping for GET /Common/swagger-ui.html
     *
     * @param registry
     */
    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        super.addResourceHandlers(registry);
    }
}
