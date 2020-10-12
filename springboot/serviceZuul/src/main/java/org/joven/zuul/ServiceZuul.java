package org.joven.zuul;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * 通过＠EnableZuulProxy 开启路由代理功能  EnableZuulProxy 已经自带了＠EnableDiscoveryClient
 * EnableDiscoveryClient 注册到服务注册中心
 */
@EnableZuulProxy
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class})
public class ServiceZuul {
    public static void main(String[] args) {
        SpringApplication.run(ServiceZuul.class, args);
    }
}
