package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.constant.IdentifierType;
import org.dows.uim.entity.*;
import org.dows.uim.request.OrgRegisterRequest;
import org.dows.uim.response.*;
import org.dows.uim.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@RequiredArgsConstructor
@Component
public class OrgApiBiz {
    private final OrgJdService orgJdService;
    private final OrgIndicatorService orgIndicatorService;

    private final OrgTreeService orgTreeService;
    private final OrgNodeService orgNodeService;

    private final OrgRegisterService orgRegisterService;

    private final AccountInstanceService accountInstanceService;
    private final AccountIdentifierService accountIdentifierService;

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
            if (Objects.nonNull(indicatorEntities)) {
                for (OrgIndicatorEntity item : indicatorEntities) {
                    OrgIndicatorResponse itemResp = new OrgIndicatorResponse();
                    BeanUtils.copyProperties(item, itemResp);
                    responseList.add(itemResp);
                }
            }
        }

        if(Objects.nonNull(jdDefaultId)) {
            OrgJdEntity itemJd = orgJdEntities.get(0);
            List<OrgIndicatorEntity> indicatorEntities = QueryChain.of(OrgIndicatorEntity.class)
                    .eq(OrgIndicatorEntity::getOrgRuleId, jdDefaultId).list();
            if (Objects.nonNull(indicatorEntities)) {
                for (OrgIndicatorEntity item : indicatorEntities) {
                    OrgIndicatorResponse itemResp = new OrgIndicatorResponse();
                    BeanUtils.copyProperties(item, itemResp);
                    responseList.add(itemResp);
                }
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

    /**
     * 注册企业账号
     *
     * @param orgRegisterRequest
     * @return
     */
    @Transactional
    public List<OrgRegisterResponse> getOrgWithRegister(List<OrgRegisterRequest> orgRegisterRequest) {
        // 批量保存组织树
        List<OrgTreeEntity> orgTreeEntities = BeanUtil.copyToList(orgRegisterRequest, OrgTreeEntity.class);
        orgTreeService.saveOrUpdateBatch(orgTreeEntities);
        // 批量保存注册信息
        List<OrgRegisterEntity> orgRegisterEntities = BeanUtil.copyToList(orgRegisterRequest, OrgRegisterEntity.class);
        // 批量保存账号信息
        List<AccountInstanceEntity> accountInstanceEntities = new ArrayList<>();
        for (int i = 0; i < orgTreeEntities.size(); i++) {
            OrgRegisterEntity orgRegisterEntity = orgRegisterEntities.get(i);
            orgRegisterEntity.setOrgTreeId(orgTreeEntities.get(i).getOrgTreeId());

            AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
            accountInstanceEntity.setIdentifier(orgRegisterEntity.getTelephone());
            accountInstanceEntity.setSuperAccount(true);
            accountInstanceEntities.add(accountInstanceEntity);
        }
        // batch save account instance
        accountInstanceService.saveBatch(accountInstanceEntities);

        List<AccountIdentifierEntity> accountIdentifierEntities = new ArrayList<>();
        for (int i = 0; i < accountInstanceEntities.size(); i++) {
            AccountInstanceEntity accountInstanceEntity = accountInstanceEntities.get(i);
            // create account identifier for phone
            AccountIdentifierEntity accountIdentifierEntity = new AccountIdentifierEntity();
            accountIdentifierEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
            accountIdentifierEntity.setIdentifier(accountInstanceEntity.getIdentifier());
            accountIdentifierEntity.setType(IdentifierType.PHONE.getType());
            accountIdentifierEntities.add(accountIdentifierEntity);
            // create account identifier for email
            accountIdentifierEntity = new AccountIdentifierEntity();
            accountIdentifierEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
            accountIdentifierEntity.setIdentifier(orgRegisterRequest.get(i).getEmail());
            accountIdentifierEntity.setType(IdentifierType.EMAIL.getType());
            accountIdentifierEntities.add(accountIdentifierEntity);
            // shell account identifier for org register
            orgRegisterEntities.get(i).setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
        }
        // batch save account identifier
        accountIdentifierService.saveBatch(accountIdentifierEntities);

        // batch save org register
        orgRegisterService.saveOrUpdateBatch(orgRegisterEntities);

        // 将当前账号关联组织节点
        List<OrgNodeEntity> orgNodeEntities = new ArrayList<>();
        for (int i = 0; i < orgRegisterEntities.size(); i++) {
            OrgNodeEntity orgNodeEntity = new OrgNodeEntity();
            orgNodeEntity.setOrgRootId(orgTreeEntities.get(i).getOrgTreeId());
            orgNodeEntity.setOrgTreeId(orgTreeEntities.get(i).getOrgTreeId());
            orgNodeEntity.setAccountInstanceId(accountInstanceEntities.get(i).getAccountInstanceId());
            orgNodeEntities.add(orgNodeEntity);
        }
        // batch save org node
        orgNodeService.saveBatch(orgNodeEntities);
        return BeanUtil.copyToList(orgRegisterEntities, OrgRegisterResponse.class);
    }

    public OrgRegisterResponse getOrgInfo(OrgRegisterRequest orgRegisterRequest) {
        OrgRegisterEntity one = orgRegisterService.getOne(QueryWrapper.create()
                .eq(OrgRegisterEntity::getEmail, orgRegisterRequest.getEmail(), Objects.nonNull(orgRegisterRequest.getEmail()))
                .eq(OrgRegisterEntity::getTelephone, orgRegisterRequest.getPhone(), Objects.nonNull(orgRegisterRequest.getPhone()))
                .eq(OrgRegisterEntity::getCreditNo, orgRegisterRequest.getCreditNo(), Objects.nonNull(orgRegisterRequest.getCreditNo()))
        );
        return BeanUtil.copyProperties(one, OrgRegisterResponse.class);
    }
}
