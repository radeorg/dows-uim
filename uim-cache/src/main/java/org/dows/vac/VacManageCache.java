package org.dows.vac;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description: </br>
 * @author: lait.zhang@gmail.com
 * @date: 9/30/2024 2:40 PM
 * @history: </br>
 * <author>      <time>      <version>    <desc>
 * 修改人姓名      修改时间        版本号       描述
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class VacManageCache {
    /*private final VacManageRepository vacManageRepository;

    private final VacInitRepository vacInitRepository;

    //@Cacheable(value = "vacManageCache:oneVacManage", key = "#eqNo + #func")
    public VacManageEntity oneVacManage(String eqNo, String func) {
        return vacManageRepository.lambdaQuery()
                .eq(VacManageEntity::getEqptNo, eqNo)
                .eq(VacManageEntity::getFunc, func)
                .oneOpt()
                .orElse(null);
    }


    public List<VacInitEntity> loadInitEqptListByAppId(String appId, EqptInitRequest eqptInitRequest){
        if(eqptInitRequest != null){
            return vacInitRepository.lambdaQuery()
                    .eq(VacInitEntity::getAppId, appId)
                    .eq(VacInitEntity::getState, 0)
                    .eq(VacInitEntity::getEqptNo,eqptInitRequest.getEqptNo())
                    .orderByAsc(VacInitEntity::getSeq)
                    .list();
        } else {
            return vacInitRepository.lambdaQuery()
                    .eq(VacInitEntity::getAppId, appId)
                    .eq(VacInitEntity::getState, 0)
                    .orderByAsc(VacInitEntity::getSeq)
                    .list();
        }
    }*/

}

