package org.dows.uim.user;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 3/20/2024 10:41 AM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@MapperScan("org.dows.uim.mapper")
@Configuration
@ComponentScan(basePackages = {"org.dows.uim.mapper", "org.dows.uim.service",
        "org.dows.uim.config", "org.dows.uim.biz", "org.dows.uim.handler", "org.dows.uim.user"})
public class UimUserAutoConfig {
}

