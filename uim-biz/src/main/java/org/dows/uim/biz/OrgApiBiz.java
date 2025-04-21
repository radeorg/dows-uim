package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.UnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.dows.rade.constant.IdentifierType;
import org.dows.rade.encrypt.EncryptApi;
import org.dows.uim.entity.*;
import org.dows.uim.exception.UimException;
import org.dows.uim.request.*;
import org.dows.uim.response.*;
import org.dows.uim.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
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
    private final OrgEmailService orgEmailService;

    private final AccountInstanceService accountInstanceService;
    private final AccountIdentifierService accountIdentifierService;

    private final EncryptApi encryptApi;

//    private final PasswordEncoder passwordEncoder;

    public JobIndicatorResponse getOrgIndicatorByIndicatorId(Long orgRootId, Long orgRuleId) {
        JobIndicatorResponse response = new JobIndicatorResponse();
        Long jdId = orgRuleId;

        //加载默认值指标查询
        Long jdDefaultId = null;
        String jobName = "##";
        List<OrgJdEntity>  orgJdEntities = QueryChain.of(OrgJdEntity.class)
                .like(OrgJdEntity::getJdName, jobName, Objects.nonNull(jobName)).list();
        if(Objects.nonNull(orgJdEntities) && orgJdEntities.size() > 0) {
            jdDefaultId = orgJdEntities.get(0).getOrgRuleId();
        }

        List<OrgIndicatorResponse> responseList = new ArrayList<>();
        if(Objects.nonNull(jdId)) {
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

    public JobIndicatorResponse getOrgIndicatorByJobName(Long orgRootId, String jobName) {
        Long jdId = null;
        JobIndicatorResponse response = new JobIndicatorResponse();
        List<OrgJdEntity> orgJdEntities = QueryChain.of(OrgJdEntity.class)
                .eq(OrgJdEntity::getOrgRootId, orgRootId, Objects.nonNull(orgRootId))
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

    public JobDescriptionResponse getJobDescriptionByJobName(Long orgRootId, String jobName) {
        JobDescriptionResponse response = new JobDescriptionResponse();

        List<OrgJdEntity> orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                .eq(OrgJdEntity::getOrgRootId, orgRootId, Objects.nonNull(orgRootId))
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
        //判断邮箱是否为空，或重复
        for(OrgRegisterRequest item : orgRegisterRequest) {
            if(StringUtils.isEmpty(item.getEmail())){
                throw new UimException(item.getOrgName() + " 邮箱为空，无法保存");
            }
            List<OrgEmailEntity> orgEmailEntities = QueryChain.of(OrgEmailEntity.class)
                    .eq(OrgEmailEntity::getEmail, item.getEmail(), Objects.nonNull(item.getEmail())).list();
            if (Objects.nonNull(orgEmailEntities) && orgEmailEntities.size() > 0) {
                throw new UimException(item.getOrgName() + "， 【" + item.getEmail() + "】邮箱已存在，无法保存");
            }

            if(Objects.nonNull(item.getTelephone())) {
                List<AccountIdentifierEntity> accountIdentifierEntities = QueryChain.of(AccountIdentifierEntity.class)
                        .eq(AccountIdentifierEntity::getIdentifier, item.getTelephone(), Objects.nonNull(item.getTelephone()))
                        .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.PHONE.getType()).list();
                if (Objects.nonNull(accountIdentifierEntities) && accountIdentifierEntities.size() > 0) {
                    throw new UimException(item.getOrgName() + "， 【" + item.getTelephone() + "】手机号已存在，无法保存");
                }
            }
        }

        // 批量保存组织树
        List<OrgTreeEntity> orgTreeEntities = BeanUtil.copyToList(orgRegisterRequest, OrgTreeEntity.class);
        orgTreeService.saveOrUpdateBatch(orgTreeEntities);
        // 批量保存注册信息
        List<OrgRegisterEntity> orgRegisterEntities = BeanUtil.copyToList(orgRegisterRequest, OrgRegisterEntity.class);
        // 批量保存账号信息
        List<AccountInstanceEntity> accountInstanceEntities = new ArrayList<>();
        // 批量报保存企业邮箱信息
        List<OrgEmailEntity> orgEmailEntities = new ArrayList<>();
        for (int i = 0; i < orgTreeEntities.size(); i++) {
            OrgRegisterEntity orgRegisterEntity = orgRegisterEntities.get(i);
            orgRegisterEntity.setOrgRootId(orgTreeEntities.get(i).getOrgTreeId());

            AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
            accountInstanceEntity.setTelephone(orgRegisterEntity.getTelephone());
            // todo 设置密码 需要加密
            String password = orgRegisterRequest.get(i).getPassword();
//            accountInstanceEntity.setPassword(passwordEncoder.encode(password));
            accountInstanceEntity.setPassword(encryptApi.getBCryptPassword(password));
            accountInstanceEntity.setSuperAccount(0);
            accountInstanceEntities.add(accountInstanceEntity);

            // 构建企业邮箱
            OrgEmailEntity orgEmailEntity = new OrgEmailEntity();
            orgEmailEntity.setEmail(orgRegisterEntity.getEmail());
            orgEmailEntity.setOrgTreeId(orgTreeEntities.get(i).getOrgTreeId());
            orgEmailEntity.setOrgRootId(orgTreeEntities.get(i).getOrgTreeId());
            orgEmailEntities.add(orgEmailEntity);
        }
        // batch save account instance
        accountInstanceService.saveBatch(accountInstanceEntities);

        List<AccountIdentifierEntity> accountIdentifierEntities = new ArrayList<>();
        for (int i = 0; i < accountInstanceEntities.size(); i++) {
            AccountInstanceEntity accountInstanceEntity = accountInstanceEntities.get(i);
            // create account identifier for phone
            AccountIdentifierEntity accountIdentifierEntity = new AccountIdentifierEntity();
            accountIdentifierEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
            accountIdentifierEntity.setIdentifier(accountInstanceEntity.getTelephone());
            accountIdentifierEntity.setIdentifierType(IdentifierType.PHONE.getType());
            accountIdentifierEntities.add(accountIdentifierEntity);
            // create account identifier for email
            accountIdentifierEntity = new AccountIdentifierEntity();
            accountIdentifierEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
            accountIdentifierEntity.setIdentifier(orgRegisterRequest.get(i).getEmail());
            accountIdentifierEntity.setIdentifierType(IdentifierType.EMAIL.getType());
            accountIdentifierEntities.add(accountIdentifierEntity);
            // shell account identifier for org register
            orgRegisterEntities.get(i).setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
        }
        // batch save account identifier
        accountIdentifierService.saveBatch(accountIdentifierEntities);
        // batch save org register
        orgRegisterService.saveOrUpdateBatch(orgRegisterEntities);
        // 批量保存企业邮箱
        orgEmailService.saveBatch(orgEmailEntities);

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
        // 查询企业邮箱
        List<String> filters = orgRegisterRequest.getFilters();
        if (filters != null) {
            // 过滤条件
            if (filters.contains("email")) {
                log.info("通过邮箱查询企业信息");
                QueryWrapper eq = QueryWrapper.create()
                        .eq(OrgEmailEntity::getEmail, orgRegisterRequest.getEmail(), Objects.nonNull(orgRegisterRequest.getEmail()))
                        .eq(OrgEmailEntity::getEmailType, orgRegisterRequest.getEmailType(), Objects.nonNull(orgRegisterRequest.getEmailType()));
                OrgEmailEntity orgEmailEntity = orgEmailService.getOne(eq);
                // 先通过邮箱查询企业邮箱信息
                if (Objects.isNull(orgEmailEntity)) {
                    log.info("未查询到企业邮箱信息");
                    return null;
                }
                // 查询注册信息
                OrgRegisterEntity orgRegisterEntity = orgRegisterService.getById(orgEmailEntity.getOrgTreeId());
                return BeanUtil.copyProperties(orgRegisterEntity, OrgRegisterResponse.class);
            } else if (filters.contains("telephone")) {
                log.info("通过手机号查询企业信息");
            }
        } else {
            log.info("通过其他条件查询企业信息");
            OrgRegisterEntity one = orgRegisterService.getOne(QueryWrapper.create()
                    .eq(OrgRegisterEntity::getEmail, orgRegisterRequest.getEmail(), Objects.nonNull(orgRegisterRequest.getEmail()))
                    .eq(OrgRegisterEntity::getTelephone, orgRegisterRequest.getTelephone(), Objects.nonNull(orgRegisterRequest.getTelephone()))
                    .eq(OrgRegisterEntity::getCreditNo, orgRegisterRequest.getCreditNo(), Objects.nonNull(orgRegisterRequest.getCreditNo()))
                    .eq(OrgRegisterEntity::getContacts, orgRegisterRequest.getContacts(), Objects.nonNull(orgRegisterRequest.getContacts()))
            );
            return BeanUtil.copyProperties(one, OrgRegisterResponse.class);
        }
        return null;
    }

    @Operation(summary = "JD上架或下架信息")
    @Transactional
    public OrgJobJDResponse upOrDownJd(OrgJDUpOrDownRequest orgJDUpOrDownRequest) throws UnavailableException {
        OrgJdEntity objEntity = new OrgJdEntity();

        if(Objects.isNull(orgJDUpOrDownRequest.getOrgJdId())){
            throw new UnavailableException("orgJdId 必填");
        }
        List<OrgJdEntity> orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                .eq(OrgJdEntity::getOrgJdId, orgJDUpOrDownRequest.getOrgJdId(), Objects.nonNull(orgJDUpOrDownRequest.getOrgJdId())).list();
        if(Objects.isNull(orgJdEntityList) || orgJdEntityList.size() == 0){
            throw new UnavailableException("orgJdId 该Jd不存在");
        }
        objEntity = orgJdEntityList.get(0);
        objEntity.setOrgJdId(orgJDUpOrDownRequest.getOrgJdId());
        objEntity.setUt(new Date());
        objEntity.setState(orgJDUpOrDownRequest.getState());
        objEntity.updateById();

        OrgJobJDResponse response = BeanUtil.copyProperties(objEntity, OrgJobJDResponse.class);

        return response;
    }

    @Operation(summary = "保存JD信息")
    @Transactional
     public OrgJobJDResponse saveOrgJdInfo(OrgJdSaveRequest orgJdSaveRequest) throws UnavailableException {
        OrgJdEntity objEntity = new OrgJdEntity();

        if(Objects.isNull(orgJdSaveRequest.getOrgRootId())){
            throw new UnavailableException("orgRootId 必填");
        }

        OrgRuleSaveRequest objEntity1 = new OrgRuleSaveRequest();
        OrgJdRequirements orgJdRequirements = orgJdSaveRequest.getOrgJdRequirements();
        if(Objects.nonNull(orgJdRequirements)){
            objEntity1.setRuleDescription(JSON.toJSONString(orgJdRequirements));
        }
        objEntity1.setOrgRuleId(orgJdSaveRequest.getOrgRuleId());
        objEntity1.setRuleName(orgJdSaveRequest.getJdName());
        objEntity1.setOrgTreeId(orgJdSaveRequest.getOrgTreeId());
        objEntity1.setAppId(orgJdSaveRequest.getAppId());
        objEntity1.setOperatorId(orgJdSaveRequest.getOperatorId());
        objEntity1.setTs(new Date());
        OrgRuleResponse response = saveOrgRule(objEntity1);

        BeanUtils.copyProperties(orgJdSaveRequest, objEntity, OrgJdEntity.class);
        objEntity.setTs(new Date());
        objEntity.setOrgTreeId(orgJdSaveRequest.getOrgTreeId());
        objEntity.setOwnerId(orgJdSaveRequest.getOrgJdRequirements().getHrAccountInstanceId());
        objEntity.setOrgRuleId(response.getOrgRuleId());
        if(Objects.isNull(objEntity.getOrgJdId())){
            //新增默认上架
            objEntity.setState(1);
        }
        objEntity.saveOrUpdate();
        orgJdSaveRequest.setOrgJdId(objEntity.getOrgJdId());

        return (OrgJobJDResponse)orgJdSaveRequest;
    }

    @Operation(summary = "获取JD列表")
    public OrgJdListResponse getJdList(OrgJdQueryRequest orgJdQueryRequest) throws UnavailableException {
        OrgJdListResponse response = new OrgJdListResponse();

        if(Objects.isNull(orgJdQueryRequest.getOrgRootId())){
            throw new UnavailableException("orgRootId 必填");
        }

        List<OrgJdEntity> orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                .eq(OrgJdEntity::getOrgRootId, orgJdQueryRequest.getOrgRootId(), Objects.nonNull(orgJdQueryRequest.getOrgRootId()))
                .eq(OrgJdEntity::getOrgTreeId, orgJdQueryRequest.getOrgTreeId(), Objects.nonNull(orgJdQueryRequest.getOrgTreeId()))
                .eq(OrgJdEntity::getOrgJdId, orgJdQueryRequest.getOrgJdId(), Objects.nonNull(orgJdQueryRequest.getOrgJdId()))
                .like(OrgJdEntity::getJdName, orgJdQueryRequest.getJdName(), Objects.nonNull(orgJdQueryRequest.getJdName())).list();
        List<OrgJobJDDetailResponse> jdList = new ArrayList<>();

        for(OrgJdEntity item : orgJdEntityList){
            OrgJobJDDetailResponse jdDetailResponse = new OrgJobJDDetailResponse();
            BeanUtils.copyProperties(item, jdDetailResponse);
            OrgRuleEntity orgRuleEntity = QueryChain.of(OrgRuleEntity.class)
                    .eq(OrgRuleEntity::getOrgRuleId, item.getOrgRuleId(), Objects.nonNull(item.getOrgRuleId())).limit(1).one();
            if(Objects.nonNull(orgRuleEntity) && Objects.nonNull(orgRuleEntity.getRuleDescription())){
                OrgJdRequirements orgJdRequirements =
                JSONObject.parseObject(orgRuleEntity.getRuleDescription(),OrgJdRequirements.class);
                jdDetailResponse.setOrgJdRequirements(orgJdRequirements);
            }
            jdList.add(jdDetailResponse);
        }

        response.setJdList(jdList);

        return response;
    }

    @Operation(summary = "保存岗位规则")
    public OrgRuleResponse saveOrgRule(OrgRuleSaveRequest orgRuleSaveRequest) {
        OrgRuleResponse response = new OrgRuleResponse();
        OrgRuleEntity objEntity = new OrgRuleEntity();
        BeanUtils.copyProperties(orgRuleSaveRequest, objEntity, OrgRuleEntity.class);
        objEntity.setTs(new Date());
        objEntity.saveOrUpdate();
        orgRuleSaveRequest.setOrgRuleId(objEntity.getOrgRuleId());

        BeanUtils.copyProperties(objEntity, response);
        return response;
    }

    @Operation(summary = "保存岗位动作")
    public OrgActionResponse saveOrgRuleAction(OrgActionSaveRequest orgActionSaveRequest) {
        OrgActionResponse response = new OrgActionResponse();
        OrgActionEntity objEntity = new OrgActionEntity();
        BeanUtils.copyProperties(orgActionSaveRequest, objEntity, OrgActionEntity.class);
        objEntity.setTs(new Date());
        objEntity.saveOrUpdate();
        orgActionSaveRequest.setOrgActionId(objEntity.getOrgActionId());

        BeanUtils.copyProperties(objEntity, response);
        return response;
    }

    @Operation(summary = "保存岗位指标")
    @Transactional
    public JobIndicatorResponse saveOrgRuleIndicator(OrgIndicatorListSaveRequest orgIndicatorListSaveRequest) {

        JobIndicatorResponse response = new JobIndicatorResponse();
        List<OrgIndicatorResponse> responseList = new ArrayList<>();
        if(Objects.nonNull(orgIndicatorListSaveRequest) && Objects.nonNull(orgIndicatorListSaveRequest.getIndicatorList())) {
            //先删除
            if(orgIndicatorListSaveRequest.getIndicatorList().size() > 0) {
                Long ruleId = orgIndicatorListSaveRequest.getIndicatorList().get(0).getOrgRuleId();
                int deletedRows = orgIndicatorService.deleteByOrgRuleId(ruleId);
                log.debug("delete records " + deletedRows);
            }
            //重新保存
            for (OrgIndicatorSaveRequest itemEntity : orgIndicatorListSaveRequest.getIndicatorList()) {
                OrgIndicatorEntity objEntity = new OrgIndicatorEntity();
                BeanUtils.copyProperties(itemEntity, objEntity, OrgIndicatorEntity.class);
                objEntity.setTs(new Date());
                objEntity.saveOrUpdate();
                itemEntity.setOrgIndicatorId(objEntity.getOrgIndicatorId());
                OrgIndicatorResponse orgIndicatorResponse = new OrgIndicatorResponse();
                BeanUtils.copyProperties(itemEntity, orgIndicatorResponse);
                responseList.add(orgIndicatorResponse);
            }
        }
        
        response.setIndicatorList(responseList);
        return response;
    }

    /**
     * 根据账号实例ID获取其所在的组织列表
     *
     * @param accountInstanceId
     * @return
     */
    public List<RootOrgResponse> getRootOrgListByAccountInstanceId(Long accountInstanceId) {
        QueryWrapper eq = QueryWrapper.create().eq(OrgNodeEntity::getAccountInstanceId, accountInstanceId);
        List<OrgNodeEntity> list = orgNodeService.list(eq);
        if (!CollectionUtil.isEmpty(list)) {
            return BeanUtil.copyToList(list, RootOrgResponse.class);
        }
        return null;
    }
}
