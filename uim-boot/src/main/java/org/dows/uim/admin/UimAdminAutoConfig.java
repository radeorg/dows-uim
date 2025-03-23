package org.dows.uim.admin;

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
@ComponentScan(basePackages = {/*"org.dows.rade",*/"org.dows.uim"})
public class UimAdminAutoConfig {
}

