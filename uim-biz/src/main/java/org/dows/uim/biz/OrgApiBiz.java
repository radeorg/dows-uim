package org.dows.uim.biz;

import com.mybatisflex.core.query.QueryChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.uim.api.response.JobDescriptionResponse;
import org.dows.uim.api.response.JobIndicatorResponse;
import org.dows.uim.api.response.OrgIndicatorResponse;
import org.dows.uim.api.response.OrgJobJDResponse;
import org.dows.uim.entity.OrgIndicatorEntity;
import org.dows.uim.entity.OrgJdEntity;
import org.dows.uim.service.OrgIndicatorService;
import org.dows.uim.service.OrgJdService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrgApiBiz {
    private final OrgJdService orgJdService;
    private final OrgIndicatorService orgIndicatorService;

    public JobIndicatorResponse getOrgIndicatorByJobName(String jobName) {
        Long jdId = null;
        JobIndicatorResponse response = new JobIndicatorResponse();
        List<OrgJdEntity> orgJdEntities = QueryChain.of(OrgJdEntity.class)
                .like(OrgJdEntity::getJdName, jobName, Objects.nonNull(jobName)).list();
        if(Objects.nonNull(orgJdEntities) && orgJdEntities.size() > 0){
            jdId = orgJdEntities.get(0).getOrgRuleId();
        }

        //加载默认值指标查询
        Long jdDefaultId = null;
        jobName = "##";
        orgJdEntities = QueryChain.of(OrgJdEntity.class)
                .like(OrgJdEntity::getDescription, jobName, Objects.nonNull(jobName)).list();
        if(Objects.nonNull(orgJdEntities) && orgJdEntities.size() > 0) {
            jdDefaultId = orgJdEntities.get(0).getOrgRuleId();
        }

        List<OrgIndicatorResponse> responseList = new ArrayList<>();
        if(Objects.nonNull(jdId)) {
            OrgJdEntity itemJd = orgJdEntities.get(0);
            List<OrgIndicatorEntity> indicatorEntities = QueryChain.of(OrgIndicatorEntity.class)
                    .eq(OrgIndicatorEntity::getOrgRuleId, jdId).list();
            if (Objects.isNull(indicatorEntities) || indicatorEntities.size() == 0) {
                return response;
            }

            for (OrgIndicatorEntity item : indicatorEntities) {
                OrgIndicatorResponse itemResp = new OrgIndicatorResponse();
                BeanUtils.copyProperties(item, itemResp);
                responseList.add(itemResp);
            }
        }

        if(Objects.nonNull(jdDefaultId)) {
            OrgJdEntity itemJd = orgJdEntities.get(0);
            List<OrgIndicatorEntity> indicatorEntities = QueryChain.of(OrgIndicatorEntity.class)
                    .eq(OrgIndicatorEntity::getOrgRuleId, jdDefaultId).list();
            if (Objects.isNull(indicatorEntities) || indicatorEntities.size() == 0) {
                return response;
            }

            for (OrgIndicatorEntity item : indicatorEntities) {
                OrgIndicatorResponse itemResp = new OrgIndicatorResponse();
                BeanUtils.copyProperties(item, itemResp);
                responseList.add(itemResp);
            }
        }

        response.setIndicatorList(responseList);

        return response;
    }

    public JobDescriptionResponse getJobDescriptionByJobName(String jobName) {
        JobDescriptionResponse response = new JobDescriptionResponse();

        List<OrgJdEntity> orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                .like(OrgJdEntity::getJdName, jobName, Objects.nonNull(jobName)).list();
        List<OrgJobJDResponse> jobList = new ArrayList<>();
        for(OrgJdEntity item : orgJdEntityList){
            OrgJobJDResponse jdItem = new OrgJobJDResponse();
            BeanUtils.copyProperties(item, jdItem);
            jobList.add(jdItem);
        }

        response.setJobList(jobList);

        return response;
    }
}
