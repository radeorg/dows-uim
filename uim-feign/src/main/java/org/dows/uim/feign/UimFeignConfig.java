package org.dows.uim.feign;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.retry.annotation.EnableRetry;

@Slf4j
//@PropertySource(factory = YamlConfigFactory.class,value = {"classpath:application-uim-feign.yml"},ignoreResourceNotFound=false,encoding="UTF-8")
@Configuration
@EnableFeignClients(basePackages={"org.dows.uim.feign","org.dows.uim.api"})
@EnableRetry
public class UimFeignConfig {

    @PostConstruct
    public void init(){
        log.info("init");
    }
//    @Bean
//    public feign.Client uimFeignClient() {
//        return new OkHttpClient();
//    }

}
