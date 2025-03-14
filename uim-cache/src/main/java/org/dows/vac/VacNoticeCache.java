package org.dows.vac;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 9/30/2024 10:38 AM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class VacNoticeCache {
    /*private final VacNoticeRepository vacNoticeRepository;

    //@Cacheable(value = "vacNoticeCache:oneVacNotice", key = "#clazz + #func")
    public VacNoticeEntity oneVacNotice(String clazz, String func) {

        // todo 抽离做缓存处理
        return vacNoticeRepository.lambdaQuery()
//                .eq(VacNoticeEntity::getAppId, appId)
                .eq(VacNoticeEntity::getFromClass, clazz)
                .eq(VacNoticeEntity::getFromFunc, func)
                .oneOpt()
                .orElse(null);
    }


    public List<VacNoticeEntity> listVacNotice(String clazz, String func) {

        // todo 抽离做缓存处理
        return vacNoticeRepository.lambdaQuery()
//                .eq(VacNoticeEntity::getAppId, appId)
                .eq(VacNoticeEntity::getFromClass, clazz)
                .eq(VacNoticeEntity::getFromFunc, func)
                .list();

    }*/


}

