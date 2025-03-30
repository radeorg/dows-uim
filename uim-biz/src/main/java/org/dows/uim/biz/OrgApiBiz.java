package org.dows.uim.biz;

import com.mybatisflex.core.query.QueryChain;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.uim.api.OrgApi;
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
public class OrgApiBiz implements OrgApi {
    private final OrgJdService orgJdService;
    private final OrgIndicatorService orgIndicatorService;

    @Override
    public JobIndicatorResponse getOrgIndicatorByJobName(String jobName) {
        JobIndicatorResponse response = new JobIndicatorResponse();
        jobName = "##";
        List<OrgJdEntity> orgJdEntities = QueryChain.of(OrgJdEntity.class)
                .like(OrgJdEntity::getDescription, jobName, Objects.nonNull(jobName)).list();
        if(Objects.isNull(orgJdEntities) || orgJdEntities.size() == 0){
            return response;
        }

        List<OrgIndicatorResponse> responseList = new ArrayList<>();
        OrgJdEntity itemJd = orgJdEntities.get(0);
        List<OrgIndicatorEntity> indicatorEntities = QueryChain.of(OrgIndicatorEntity.class)
                .eq(OrgIndicatorEntity::getOrgRuleId, itemJd.getOrgRuleId(), Objects.nonNull(itemJd.getOrgJdId())).list();
        if (Objects.isNull(indicatorEntities) || indicatorEntities.size() == 0) {
            return response;
        }

        for (OrgIndicatorEntity item : indicatorEntities) {
            OrgIndicatorResponse itemResp = new OrgIndicatorResponse();
            BeanUtils.copyProperties(item, itemResp);
            responseList.add(itemResp);
        }

        response.setIndicatorList(responseList);

        return response;
    }

    @Override
    public JobDescriptionResponse getJobDescriptionByJobName(String jobName) {
        JobDescriptionResponse response = new JobDescriptionResponse();

        List<OrgJdEntity> orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                .like(OrgJdEntity::getDescription, jobName, Objects.nonNull(jobName)).list();
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
