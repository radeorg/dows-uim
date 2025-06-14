package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mybatisflex.core.paginate.Page;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.core.row.DbChain;
import com.mybatisflex.core.update.UpdateChain;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.UnavailableException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.dows.pojo.enums.*;
import org.dows.rade.aac.AacContext;
import org.dows.rade.cache.RadeCache;
import org.dows.rade.constant.IdentifierType;
import org.dows.rade.context.AppContext;
import org.dows.rade.encrypt.EncryptApi;
import org.dows.rade.util.DateUtil;
import org.dows.rade.web.Response;
import org.dows.uim.constant.CommonDelEnum;
import org.dows.uim.entity.*;
import org.dows.uim.exception.UimException;
import org.dows.uim.request.*;
import org.dows.uim.request.JdKeyWord.CompanyInfo;
import org.dows.uim.request.JdKeyWord.JDSaveRequest;
import org.dows.uim.request.JdKeyWord.SalaryBenefitInfo;
import org.dows.uim.response.*;
import org.dows.uim.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private final AacContext aacContext;

    private enum ChangeField {
        SCALE, FUNDING_STAGE, PROJECT_TYPE, PROJECT_PROGRESS, SIMILAR_POSITIONS
    }

    private final RadeCache radeCache;

    private final ObjectMapper objectMapper;

//    private final PasswordEncoder passwordEncoder;

    public JobIndicatorResponse getOrgIndicatorByIndicatorId(Long orgRootId, Long orgRuleId) {
        JobIndicatorResponse response = new JobIndicatorResponse();
        Long jdId = orgRuleId;

        //加载默认值指标查询
        Long jdDefaultId = null;
        String jobName = "##";
        List<OrgJdEntity> orgJdEntities = QueryChain.of(OrgJdEntity.class)
                .like(OrgJdEntity::getJdName, jobName, Objects.nonNull(jobName))
                .eq(OrgJdEntity::getDeleted, CommonDelEnum.NORMAL.getCode()).list();
        if (Objects.nonNull(orgJdEntities) && orgJdEntities.size() > 0) {
            jdDefaultId = orgJdEntities.get(0).getOrgRuleId();
        }

        List<OrgIndicatorResponse> responseList = new ArrayList<>();
        if (Objects.nonNull(jdId)) {
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

        if (Objects.nonNull(jdDefaultId)) {
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

    public JobIndicatorResponse getOrgIndicatorByJobName(Long orgJdId,Long orgRootId, String jobName, String jdNo) {
        Long jdId = null;
        JobIndicatorResponse response = new JobIndicatorResponse();
        List<OrgJdEntity> orgJdEntities = QueryChain.of(OrgJdEntity.class)
                .eq(OrgJdEntity::getOrgRootId, orgRootId, Objects.nonNull(orgRootId))
                .eq(OrgJdEntity::getOrgJdId, orgJdId, Objects.nonNull(orgJdId))
                .eq(OrgJdEntity::getJdNo, jdNo, Objects.nonNull(jdNo))
                .eq(OrgJdEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                .like(OrgJdEntity::getJdName, jobName, Objects.nonNull(jobName)).list();
        if (Objects.nonNull(orgJdEntities) && orgJdEntities.size() > 0) {
            jdId = orgJdEntities.get(0).getOrgRuleId();
        }

        //加载默认值指标查询
        Long jdDefaultId = null;
        jobName = "##";
        orgJdEntities = QueryChain.of(OrgJdEntity.class)
                .like(OrgJdEntity::getDescription, jobName, Objects.nonNull(jobName))
                .list();
        if (Objects.nonNull(orgJdEntities) && orgJdEntities.size() > 0) {
            jdDefaultId = orgJdEntities.get(0).getOrgRuleId();
        }

        List<OrgIndicatorResponse> responseList = new ArrayList<>();
        if (Objects.nonNull(jdId)) {
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

        if (Objects.nonNull(jdDefaultId)) {
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

    public JobDescriptionResponse getJobDescriptionByJobName(Long orgJdId,Long orgRootId, String jobName, String jdNo) {
        JobDescriptionResponse response = new JobDescriptionResponse();

        List<OrgJdEntity> orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                .eq(OrgJdEntity::getOrgRootId, orgRootId, Objects.nonNull(orgRootId))
                .eq(OrgJdEntity::getOrgJdId, orgJdId, Objects.nonNull(orgJdId))
                .eq(OrgJdEntity::getJdNo, jdNo, Objects.nonNull(jdNo))
                .eq(OrgJdEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                .like(OrgJdEntity::getJdName, jobName, Objects.nonNull(jobName)).list();
        List<OrgJobJDResponse> jobList = new ArrayList<>();
        for (OrgJdEntity item : orgJdEntityList) {
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
    public OrgRegisterResponse getOrgWithRegister(OrgRegisterRequest orgRegisterRequest) {
        //判断邮箱是否为空，或重复
        if (StringUtils.isEmpty(orgRegisterRequest.getEmail())) {
            throw new UimException(" 邮箱为空，无法保存");
        }
        //判断邮箱是否为空，或重复
        if (StringUtils.isEmpty(orgRegisterRequest.getOrgName())) {
            throw new UimException(" 组织名称空，无法保存");
        }
        //判断邮箱是否为空，或重复
        if (StringUtils.isEmpty(orgRegisterRequest.getTelephone())) {
            throw new UimException(" 手机号为空，无法保存");
        }
        List<OrgEmailEntity> orgEmailEntities = QueryChain.of(OrgEmailEntity.class)
                .eq(OrgEmailEntity::getEmail, orgRegisterRequest.getEmail(), Objects.nonNull(orgRegisterRequest.getEmail())).list();
        if (Objects.nonNull(orgEmailEntities) && !orgEmailEntities.isEmpty()) {
            throw new UimException(orgRegisterRequest.getOrgName() + "， 【" + orgRegisterRequest.getEmail() + "】邮箱已存在，无法保存");
        }
        if (Objects.nonNull(orgRegisterRequest.getTelephone())) {
            List<AccountIdentifierEntity> accountIdentifierEntities = QueryChain.of(AccountIdentifierEntity.class)
                    .eq(AccountIdentifierEntity::getIdentifier, orgRegisterRequest.getTelephone(), Objects.nonNull(orgRegisterRequest.getTelephone()))
                    .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.PHONE.getType()).list();
            if (Objects.nonNull(accountIdentifierEntities) && !accountIdentifierEntities.isEmpty()) {
                throw new UimException(orgRegisterRequest.getOrgName() + "， 【" + orgRegisterRequest.getTelephone() + "】手机号已存在，无法保存");
            }
        }
        OrgTreeEntity one = orgTreeService
                .getOne(QueryWrapper.create().eq(OrgTreeEntity::getOrgName, orgRegisterRequest.getOrgName()));
        if (Objects.nonNull(one)) {
            throw new UimException(orgRegisterRequest.getOrgName() + "， 组织名称已存在，无法保存");
        }

        AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
        accountInstanceEntity.setTelephone(orgRegisterRequest.getTelephone());
        // todo 设置密码 需要加密
        accountInstanceEntity.setPassword(encryptApi.getBCryptPassword(orgRegisterRequest.getPassword()));
        // 超级账号
        accountInstanceEntity.setNickname(orgRegisterRequest.getContacts());
        accountInstanceEntity.setSuperAccount(1);
        accountInstanceService.save(accountInstanceEntity);

        // 批量保存组织树
        OrgTreeEntity orgTreeEntity = BeanUtil.copyProperties(orgRegisterRequest, OrgTreeEntity.class);
        orgTreeEntity.setOperatorId(accountInstanceEntity.getAccountInstanceId());
        orgTreeService.save(orgTreeEntity);

        // 构建企业邮箱
        OrgEmailEntity orgEmailEntity = new OrgEmailEntity();
        orgEmailEntity.setEmail(orgRegisterRequest.getEmail());
        orgEmailEntity.setOrgRootId(orgTreeEntity.getOrgTreeId());
        orgEmailEntity.setOrgTreeId(orgTreeEntity.getOrgTreeId());
        // 批量保存企业邮箱
        orgEmailService.save(orgEmailEntity);

        List<AccountIdentifierEntity> accountIdentifierEntities = new ArrayList<>();
        // create account identifier for phone
        AccountIdentifierEntity accountIdentifierEntity = new AccountIdentifierEntity();
        accountIdentifierEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
        accountIdentifierEntity.setIdentifier(accountInstanceEntity.getTelephone());
        accountIdentifierEntity.setIdentifierType(IdentifierType.PHONE.getType());
        accountIdentifierEntity.setOperatorId(accountInstanceEntity.getAccountInstanceId());
        accountIdentifierEntities.add(accountIdentifierEntity);
        // create account identifier for email
        accountIdentifierEntity = new AccountIdentifierEntity();
        accountIdentifierEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
        accountIdentifierEntity.setIdentifier(orgRegisterRequest.getEmail());
        accountIdentifierEntity.setIdentifierType(IdentifierType.EMAIL.getType());
        accountIdentifierEntity.setOperatorId(accountInstanceEntity.getAccountInstanceId());
        accountIdentifierEntities.add(accountIdentifierEntity);
        // batch save account identifier
        accountIdentifierService.saveBatch(accountIdentifierEntities);

        // 批量保存注册信息
        OrgRegisterEntity orgRegisterEntity = BeanUtil.copyProperties(orgRegisterRequest, OrgRegisterEntity.class);
        orgRegisterEntity.setOrgRootId(orgTreeEntity.getOrgTreeId());
        // shell account identifier for org register
        orgRegisterEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
        // batch save org register
        orgRegisterService.save(orgRegisterEntity);

        // 将当前账号关联组织节点
        OrgNodeEntity orgNodeEntity = new OrgNodeEntity();
        orgNodeEntity.setOrgRootId(orgTreeEntity.getOrgTreeId());
        orgNodeEntity.setOrgTreeId(orgTreeEntity.getOrgTreeId());
        orgNodeEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
        orgNodeEntity.setOperatorId(accountInstanceEntity.getAccountInstanceId());
        // batch save org node
        orgNodeService.save(orgNodeEntity);
        return BeanUtil.copyProperties(orgRegisterEntity, OrgRegisterResponse.class);
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
        for (OrgRegisterRequest item : orgRegisterRequest) {
            if (StringUtils.isEmpty(item.getEmail())) {
                throw new UimException(item.getOrgName() + " 邮箱为空，无法保存");
            }
            List<OrgEmailEntity> orgEmailEntities = QueryChain.of(OrgEmailEntity.class)
                    .eq(OrgEmailEntity::getEmail, item.getEmail(), Objects.nonNull(item.getEmail())).list();
            if (Objects.nonNull(orgEmailEntities) && !orgEmailEntities.isEmpty()) {
                throw new UimException(item.getOrgName() + "， 【" + item.getEmail() + "】邮箱已存在，无法保存");
            }

            if (Objects.nonNull(item.getTelephone())) {
                List<AccountIdentifierEntity> accountIdentifierEntities = QueryChain.of(AccountIdentifierEntity.class)
                        .eq(AccountIdentifierEntity::getIdentifier, item.getTelephone(), Objects.nonNull(item.getTelephone()))
                        .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.PHONE.getType()).list();
                if (Objects.nonNull(accountIdentifierEntities) && !accountIdentifierEntities.isEmpty()) {
                    throw new UimException(item.getOrgName() + "， 【" + item.getTelephone() + "】手机号已存在，无法保存");
                }
            }
            /*OrgTreeEntity one = orgTreeService
                    .getOne(QueryWrapper.create().eq(OrgTreeEntity::getOrgName, item.getOrgName()));*/
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
            // 设置为超级账号
            accountInstanceEntity.setSuperAccount(1);
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
                OrgRegisterEntity orgRegisterEntity = orgRegisterService.getOne(QueryWrapper.create()
                        .eq(OrgRegisterEntity::getOrgRootId, orgEmailEntity.getOrgTreeId()));
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
                    .eq(OrgRegisterEntity::getOrgRootId, orgRegisterRequest.getOrgRootId(), Objects.nonNull(orgRegisterRequest.getOrgRootId()))
            );
            return BeanUtil.copyProperties(one, OrgRegisterResponse.class);
        }
        return null;
    }

    @Operation(summary = "JD上架或下架信息")
    @Transactional
    public OrgJobJDResponse upOrDownJd(OrgJDUpOrDownRequest orgJDUpOrDownRequest) throws UnavailableException {
        OrgJdEntity objEntity = new OrgJdEntity();

        if (Objects.isNull(orgJDUpOrDownRequest.getOrgJdId())) {
            throw new UnavailableException("orgJdId 必填");
        }
        List<OrgJdEntity> orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                .eq(OrgJdEntity::getOrgJdId, orgJDUpOrDownRequest.getOrgJdId(), Objects.nonNull(orgJDUpOrDownRequest.getOrgJdId())).list();
        if (Objects.isNull(orgJdEntityList) || orgJdEntityList.size() == 0) {
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

    @Operation(summary = "JDCode增加")
    public JdCodeResponse addJdCode(HrmJdCodeQueryRequest hrmJdCodeQueryRequest) throws UnavailableException {
        if (Objects.isNull(hrmJdCodeQueryRequest.getValue())) {
            throw new UnavailableException("value 必填");
        }
        if (Objects.isNull(hrmJdCodeQueryRequest.getCodeType())) {
            throw new UnavailableException("codeType 必填");
        }
        List<HrmJdCodeEntity> jdCodeEntities =  QueryChain.of(HrmJdCodeEntity.class)
                .select(HrmJdCodeEntity::getCode, HrmJdCodeEntity::getValue)
                .eq(HrmJdCodeEntity::getCodeType, hrmJdCodeQueryRequest.getCodeType())
                .eq(HrmJdCodeEntity::getAppId, AppContext.getAppId())
                .eq(HrmJdCodeEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                .orderBy(HrmJdCodeEntity::getCode,false)
                .list();
        if (CollectionUtil.isNotEmpty(jdCodeEntities)) {
            // 检查是否存在匹配的 value（忽略大小写）
            boolean exists =
                    jdCodeEntities.stream()
                            .map(HrmJdCodeEntity::getValue)
                            .filter(Objects::nonNull)
                            .anyMatch(value -> value.equalsIgnoreCase(hrmJdCodeQueryRequest.getValue()));

            if (exists) {
                throw new UnavailableException("值 '" + hrmJdCodeQueryRequest.getValue() + "' 已存在");
            }
            Integer  nextCode = jdCodeEntities.get(0).getCode() + 1;
            HrmJdCodeEntity newEntity = HrmJdCodeEntity.builder()
                    .code(nextCode)
                    .value(hrmJdCodeQueryRequest.getValue())
                    .codeType(hrmJdCodeQueryRequest.getCodeType())
                    .appId(AppContext.getAppId())
                    .ut(new Date())
                    .ts(new Date())
                    .ownerId(aacContext.getAacUser().getUserId())
                    .deleted(CommonDelEnum.NORMAL.getCode())
                    .build();

            boolean flag = newEntity.save();
            if(flag) {
                if("projectType".equals(hrmJdCodeQueryRequest.getCodeType())){
                Map<Integer,String> projectTypeMap = QueryChain.of(HrmJdCodeEntity.class)
                        .eq(HrmJdCodeEntity::getCodeType, hrmJdCodeQueryRequest.getCodeType())
                        .eq(HrmJdCodeEntity::getAppId, AppContext.getAppId())
                        .eq(HrmJdCodeEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                        .list()
                        .stream()
                        .collect(Collectors.toMap(
                                HrmJdCodeEntity::getCode,
                                HrmJdCodeEntity::getValue
                        ));
                String  projectTypeKey = "JD:projectType:"+AppContext.getAppId();
                radeCache.set(projectTypeKey, projectTypeMap);
                }

                return JdCodeResponse.builder().code(nextCode).value(hrmJdCodeQueryRequest.getValue()).codeType(hrmJdCodeQueryRequest.getCodeType()).build();

            }else {
                throw new UnavailableException("自定义类型保存失败");
            }


        }else {
            throw new UnavailableException("暂不支持创建新类型码表值");
        }


    }

    @Operation(summary = "JD删除")
    @Transactional
    public Boolean deleteJd(Long orgJdId) throws UnavailableException {
        if (Objects.isNull(orgJdId)) {
            throw new UnavailableException("orgJdId 必填");
        }

        List<OrgJdEntity> orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                .eq(OrgJdEntity::getOrgJdId, orgJdId).list();
        if (Objects.isNull(orgJdEntityList) || orgJdEntityList.size() == 0) {
            throw new UnavailableException("orgJdId 该Jd不存在");
        }

        boolean updateRec = UpdateChain.of(OrgJdEntity.class)
                .set(OrgJdEntity::getDeleted, CommonDelEnum.DELETE.getCode())
                .set(OrgJdEntity::getOperatorId, aacContext.getAacUser().getUserId())
                .eq(OrgJdEntity::getOrgJdId, orgJdId).update();

        return updateRec;
    }

    @Operation(summary = "保存JD信息")
    @Transactional
    public OrgJobJDResponse saveOrgJdInfo(OrgJdSaveRequest orgJdSaveRequest) throws UnavailableException {
        OrgJdEntity objEntity = new OrgJdEntity();

        Long orgRootId = aacContext.getAacUser().getOrgRootId();
        orgJdSaveRequest.setOrgRootId(orgRootId);

        if (Objects.isNull(orgJdSaveRequest.getOrgRootId())) {
            throw new UnavailableException("orgRootId 必填");
        }

        OrgRuleSaveRequest objEntity1 = new OrgRuleSaveRequest();
        OrgJdRequirements orgJdRequirements = orgJdSaveRequest.getOrgJdRequirements();
        if (Objects.nonNull(orgJdRequirements)) {
            objEntity1.setRuleDescription(JSON.toJSONString(orgJdRequirements));
        }
        objEntity1.setOrgRuleId(orgJdSaveRequest.getOrgRuleId());
        objEntity1.setRuleName(orgJdSaveRequest.getJdName());
        objEntity1.setOrgTreeId(orgJdSaveRequest.getOrgTreeId());
        objEntity1.setAppId(orgJdSaveRequest.getAppId());
        objEntity1.setOperatorId(aacContext.getAacUser().getUserId());
        objEntity1.setTs(new Date());
        OrgRuleResponse response = saveOrgRule(objEntity1);

        BeanUtils.copyProperties(orgJdSaveRequest, objEntity, OrgJdEntity.class);
        objEntity.setJdNo("JD"+UUID.randomUUID().toString().replace("-", ""));
        log.info("JdNo是+++++++++++++++++++++",objEntity.getJdNo());
        objEntity.setTs(new Date());
        objEntity.setOrgTreeId(orgJdSaveRequest.getOrgTreeId());
        if(Objects.isNull(orgJdSaveRequest.getOwnerId())) {
            objEntity.setOwnerId(orgJdSaveRequest.getOrgJdRequirements().getHrAccountInstanceId());
        }
        objEntity.setOrgRuleId(response.getOrgRuleId());
        objEntity.setDeleted(CommonDelEnum.NORMAL.getCode());
        objEntity.setOperatorId(aacContext.getAacUser().getUserId());
        if (Objects.isNull(objEntity.getOrgJdId())) {
            //新增默认上架
            objEntity.setState(1);
        }
        objEntity.saveOrUpdate();
        orgJdSaveRequest.setOrgRuleId(objEntity.getOrgRuleId());
        orgJdSaveRequest.setOrgJdId(objEntity.getOrgJdId());

        return BeanUtil.copyProperties(orgJdSaveRequest, OrgJobJDResponse.class);
    }

    @Operation(summary = "获取JD分页列表")
    public Page<OrgJobJDDetailResponse>  getJdPage(OrgJdPageQueryRequest orgJdQueryRequest) throws UnavailableException {
        OrgJdPageResponse response = new OrgJdPageResponse();
        Long orgRootId = aacContext.getAacUser().getOrgRootId();
        orgJdQueryRequest.setOrgRootId(orgRootId);

        if (Objects.isNull(orgJdQueryRequest.getOrgRootId())) {
            throw new UnavailableException("orgRootId 必填");
        }

        // 创建分页对象
        Page<OrgJobJDDetailResponse> page = new Page<>(
                Long.valueOf(orgJdQueryRequest.getPageNum()),
                Long.valueOf(orgJdQueryRequest.getPageSize())
        );

        Page<OrgJobJDDetailResponse> orgJdEntityList = new Page<>();
        if(Objects.nonNull(orgJdQueryRequest.getStartDate()) && Objects.nonNull(orgJdQueryRequest.getEndDate())) {
            try {
                Date endDate = DateUtil.parseDate(orgJdQueryRequest.getEndDate());
                endDate = DateUtils.addDays(endDate, 1);
                orgJdQueryRequest.setEndDate(DateUtil.formateDate(endDate));
            } catch (Exception e) {
                log.error(e.getLocalizedMessage());
            }
            orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                    .eq(OrgJdEntity::getOrgRootId, orgJdQueryRequest.getOrgRootId(), Objects.nonNull(orgJdQueryRequest.getOrgRootId()))
                    .eq(OrgJdEntity::getOrgTreeId, orgJdQueryRequest.getOrgTreeId(), Objects.nonNull(orgJdQueryRequest.getOrgTreeId()))
                    .eq(OrgJdEntity::getOrgJdId, orgJdQueryRequest.getOrgJdId(), Objects.nonNull(orgJdQueryRequest.getOrgJdId()))
                    .like(OrgJdEntity::getJdName, orgJdQueryRequest.getJdName(), Objects.nonNull(orgJdQueryRequest.getJdName()))
                    .notIn(OrgJdEntity::getJdName, "##")
                    .eq(OrgJdEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                    .between(OrgJdEntity::getTs, orgJdQueryRequest.getStartDate(), orgJdQueryRequest.getEndDate())
                    .orderBy(OrgJdEntity::getUt, false)
                    .pageAs(page, OrgJobJDDetailResponse.class);

        }else{
            orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                    .eq(OrgJdEntity::getOrgRootId, orgJdQueryRequest.getOrgRootId(), Objects.nonNull(orgJdQueryRequest.getOrgRootId()))
                    .eq(OrgJdEntity::getOrgTreeId, orgJdQueryRequest.getOrgTreeId(), Objects.nonNull(orgJdQueryRequest.getOrgTreeId()))
                    .eq(OrgJdEntity::getOrgJdId, orgJdQueryRequest.getOrgJdId(), Objects.nonNull(orgJdQueryRequest.getOrgJdId()))
                    .like(OrgJdEntity::getJdName, orgJdQueryRequest.getJdName(), Objects.nonNull(orgJdQueryRequest.getJdName()))
                    .notIn(OrgJdEntity::getJdName, "##")
                    .eq(OrgJdEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                    .orderBy(OrgJdEntity::getUt, false)
                    .pageAs(page, OrgJobJDDetailResponse.class);
        }

        for (OrgJobJDDetailResponse item : orgJdEntityList.getRecords()) {
            OrgJobJDDetailResponse jdDetailResponse = new OrgJobJDDetailResponse();
            BeanUtils.copyProperties(item, jdDetailResponse);
            OrgRuleEntity orgRuleEntity = QueryChain.of(OrgRuleEntity.class)
                    .eq(OrgRuleEntity::getOrgRuleId, item.getOrgRuleId(), Objects.nonNull(item.getOrgRuleId())).limit(1).one();
            OrgJdRequirements orgJdRequirements = new OrgJdRequirements();
            if(Objects.nonNull(orgRuleEntity) && Objects.nonNull(orgRuleEntity.getRuleDescription())){
                try {
                    orgJdRequirements =
                            JSONObject.parseObject(orgRuleEntity.getRuleDescription(), OrgJdRequirements.class);
                } catch (Exception e) {
                }
            }
            if(Objects.isNull(orgJdRequirements)){
                orgJdRequirements = new OrgJdRequirements();
            }
            item.setOrgJdRequirements(orgJdRequirements);

            OrgJdRelatedCountResponse orgJdRelatedCountResponse = new OrgJdRelatedCountResponse();
            List<OrgJdRelatedCountResponse> resumeCountList1 = DbChain.table("resume_instance").select("org_jd_id as orgJdId",
                            "count(1) as resumeCount ")
                    .eq("org_jd_id", item.getOrgJdId()).groupBy("org_jd_id").listAs(OrgJdRelatedCountResponse.class);
            if(Objects.nonNull(resumeCountList1) && resumeCountList1.size() > 0){
                orgJdRelatedCountResponse.setResumeCount(resumeCountList1.get(0).getResumeCount());
            }

            List<OrgJdRelatedCountResponse> resumeCountList2 = DbChain.table("resume_instance").select("org_jd_id as orgJdId",
                            "count(1) as interviewedCount ")
                    .eq("org_jd_id", item.getOrgJdId())
                    .isNotNull("interview_invite_id").groupBy("org_jd_id").listAs(OrgJdRelatedCountResponse.class);
            if(Objects.nonNull(resumeCountList2) && resumeCountList2.size() > 0){
                orgJdRelatedCountResponse.setInterviewedCount(resumeCountList2.get(0).getInterviewedCount());
            }

            List<OrgJdRelatedCountResponse> resumeCountList3 = DbChain.table("resume_instance").select("org_jd_id as orgJdId",
                            "count(1) as matchCount ")
                    .eq("org_jd_id", item.getOrgJdId())
                    .between("match_percent", 70, 100).groupBy("org_jd_id").listAs(OrgJdRelatedCountResponse.class);

            if(Objects.nonNull(resumeCountList3) && resumeCountList3.size() > 0){
                orgJdRelatedCountResponse.setMatchCount(resumeCountList3.get(0).getMatchCount());
            }
            item.setOrgJdRelatedCountResponse(orgJdRelatedCountResponse);

        }

        response.setJdList(orgJdEntityList);

        return orgJdEntityList;
    }


    @Operation(summary = "获取JD列表")
    public OrgJdListResponse getJdList(OrgJdQueryRequest orgJdQueryRequest) throws UnavailableException {
        OrgJdListResponse response = new OrgJdListResponse();
        Long orgRootId = aacContext.getAacUser().getOrgRootId();
        orgJdQueryRequest.setOrgRootId(orgRootId);

        if (Objects.isNull(orgJdQueryRequest.getOrgRootId())) {
            throw new UnavailableException("orgRootId 必填");
        }

        List<OrgJdEntity> orgJdEntityList = QueryChain.of(OrgJdEntity.class)
                    .eq(OrgJdEntity::getOrgRootId, orgJdQueryRequest.getOrgRootId(), Objects.nonNull(orgJdQueryRequest.getOrgRootId()))
                    .eq(OrgJdEntity::getOrgTreeId, orgJdQueryRequest.getOrgTreeId(), Objects.nonNull(orgJdQueryRequest.getOrgTreeId()))
                    .eq(OrgJdEntity::getOrgJdId, orgJdQueryRequest.getOrgJdId(), Objects.nonNull(orgJdQueryRequest.getOrgJdId()))
                    .like(OrgJdEntity::getJdName, orgJdQueryRequest.getJdName(), Objects.nonNull(orgJdQueryRequest.getJdName()))
                    .eq(OrgJdEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                    .orderBy(OrgJdEntity::getUt, false)
                    .list();

        List<OrgJobJDDetailResponse> jdList = new ArrayList<>();

        for (OrgJdEntity item : orgJdEntityList) {
            OrgJobJDDetailResponse jdDetailResponse = new OrgJobJDDetailResponse();
            BeanUtils.copyProperties(item, jdDetailResponse);
            OrgRuleEntity orgRuleEntity = QueryChain.of(OrgRuleEntity.class)
                    .eq(OrgRuleEntity::getOrgRuleId, item.getOrgRuleId(), Objects.nonNull(item.getOrgRuleId())).limit(1).one();
            if(Objects.nonNull(orgRuleEntity) && Objects.nonNull(orgRuleEntity.getRuleDescription())){
                try {
                    OrgJdRequirements orgJdRequirements =
                            JSONObject.parseObject(orgRuleEntity.getRuleDescription(), OrgJdRequirements.class);
                    jdDetailResponse.setOrgJdRequirements(orgJdRequirements);
                } catch (Exception e) {
                }
            }
            jdList.add(jdDetailResponse);
        }

        response.setJdList(jdList);

        return response;
    }

    @Operation(summary = "获取JD码值列表")
    public List<JdCodeResponse> getJdCodeList(HrmJdCodeQueryRequest hrmJdCodeQueryRequest) throws UnavailableException {
        if (Objects.isNull(hrmJdCodeQueryRequest.getCodeType())) {
            throw new UnavailableException("CodeType 必填");
        }

        return QueryChain.of(HrmJdCodeEntity.class)
                .select(HrmJdCodeEntity::getCode, HrmJdCodeEntity::getValue)
                .eq(HrmJdCodeEntity::getCodeType, hrmJdCodeQueryRequest.getCodeType())
                .like(HrmJdCodeEntity::getValue,hrmJdCodeQueryRequest.getValue(),Objects.nonNull(hrmJdCodeQueryRequest.getValue()))
                .eq(HrmJdCodeEntity::getAppId, AppContext.getAppId())
                .eq(HrmJdCodeEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                .list()
                .stream()
                .map(entity -> JdCodeResponse.builder()
                        .code(entity.getCode())
                        .value(entity.getValue())
                        .codeType(entity.getCodeType())
                        .build())
                .collect(Collectors.toList());


    }
    @Operation(summary = "获取JD码值列表")
    public List<JdCodeResponse> getJdCodeByList(HrmJdCodeQueryListRequest hrmJdCodeQueryRequest) throws UnavailableException {
        if (Objects.isNull(hrmJdCodeQueryRequest.getCodeType())) {
            throw new UnavailableException("CodeType 必填");
        }

        return QueryChain.of(HrmJdCodeEntity.class)
                .select(HrmJdCodeEntity::getCode, HrmJdCodeEntity::getValue)
                .eq(HrmJdCodeEntity::getCodeType, hrmJdCodeQueryRequest.getCodeType())
                .in(HrmJdCodeEntity::getValue,hrmJdCodeQueryRequest.getValues(),CollectionUtil.isNotEmpty(hrmJdCodeQueryRequest.getValues()))
                .in(HrmJdCodeEntity::getCode,hrmJdCodeQueryRequest.getCodes(),CollectionUtil.isNotEmpty(hrmJdCodeQueryRequest.getCodes()))
                .eq(HrmJdCodeEntity::getAppId, AppContext.getAppId())
                .eq(HrmJdCodeEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                .list()
                .stream()
                .map(entity -> JdCodeResponse.builder()
                        .code(entity.getCode())
                        .value(entity.getValue())
                        .codeType(entity.getCodeType())
                        .build())
                .collect(Collectors.toList());


    }


    private Long  saveJd(Integer scale,Integer fundingStage,Integer projectType,Integer projectProgress,String similarPositions){
        // 创建新实体（使用Builder模式）
        HrmEnterpriseSituationEntity newEntity = HrmEnterpriseSituationEntity.builder()
                // 设置变更字段
                .companyScale(scale)
                .financingStage(fundingStage)
                .projectType(projectType)
                .projectProgress(projectProgress)
                .similarPositions(similarPositions)
                // 继承未变更字段
                .appId(AppContext.getAppId())
                .deleted(0)
                .ownerId(aacContext.getAacUser().getUserId())
                // 时间戳
                .ts(new Date())
                .ut(new Date())
                .build();
        newEntity.save();
        return newEntity.getHrmEnterpriseSituationId();
    }

    private String generateChangeRemark(Map<ChangeField, String> changes, HrmEnterpriseSituationEntity existing) {
        StringBuilder remark = new StringBuilder("企业信息变更：");

        for (Map.Entry<ChangeField, String> entry : changes.entrySet()) {
            String fieldName = getFieldChineseName(entry.getKey());
            String oldValue = getFieldValue(entry.getKey(), existing);
            String newValue = entry.getValue();

            remark.append(String.format("\n【%s】 %s → %s",
                    fieldName, oldValue, newValue));
        }

        return remark.toString();
    }

    // 辅助方法：获取字段中文名
    private String getFieldChineseName(ChangeField field) {
        switch (field) {
            case SCALE: return "公司规模";
            case FUNDING_STAGE: return "融资阶段";
            case PROJECT_TYPE: return "项目类型";
            case PROJECT_PROGRESS: return "项目进展";
            case SIMILAR_POSITIONS: return "相似岗位";
            default: return field.name();
        }
    }

    // 辅助方法：获取字段旧值
    private String getFieldValue(ChangeField field, HrmEnterpriseSituationEntity entity) {
        switch (field) {
            case SCALE: return CompanyScaleEnum.getByCode(entity.getCompanyScale()).getDescription();
            case FUNDING_STAGE: return FinancingStageEnum.getByCode(entity.getFinancingStage()).getDescription();
            case PROJECT_TYPE: return getProjectTypeDescription(entity.getProjectType());
            case PROJECT_PROGRESS: return ProjectProgressEnum.getByCode(entity.getProjectProgress()).getDescription();
            case SIMILAR_POSITIONS: return entity.getSimilarPositions();
            default: return "";
        }
    }

    private String getProjectTypeDescription(Integer code){
        String  projectTypeKey = "JD:projectType:"+AppContext.getAppId();
        Map<Integer,String> map = (Map<Integer,String>)radeCache.get(projectTypeKey);
        return  map.get(code);
    }

    @Operation(summary = "查询企业情况")
    public CompanyInfoResponse queryCompany(){
        CompanyInfoResponse response = new CompanyInfoResponse();
        List<HrmEnterpriseSituationEntity> situationEntitys = QueryChain.of(HrmEnterpriseSituationEntity.class)
                .eq(HrmEnterpriseSituationEntity::getAppId, AppContext.getAppId())
                .eq(HrmEnterpriseSituationEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                .orderBy(HrmEnterpriseSituationEntity::getUt,false)
                .list();

        if(CollectionUtil.isNotEmpty(situationEntitys)){
            HrmEnterpriseSituationEntity situationEntity = situationEntitys.get(0);
            response.setHrmEnterpriseSituationId(situationEntity.getHrmEnterpriseSituationId());
            if(Objects.nonNull(situationEntity.getCompanyScale())){
                response.setScale(CompanyScaleEnum.getByCode(situationEntity.getCompanyScale()).getDescription());
            }
            if(Objects.nonNull(situationEntity.getFinancingStage())){
                response.setFundingStage(FinancingStageEnum.getByCode(situationEntity.getFinancingStage()).getDescription());
            }
            response.setProjectType(getProjectTypeDescription(situationEntity.getProjectType()));
            response.setProjectProgress(ProjectProgressEnum.getByCode(situationEntity.getProjectProgress()).getDescription());
            response.setSimilarPositions(situationEntity.getSimilarPositions());

        }
        return response;

    }



    @Operation(summary = "获取单个JD码值")
    public JdCodeResponse getJdCodeOne(HrmJdCodeQueryRequest hrmJdCodeQueryRequest) throws UnavailableException {
        if (Objects.isNull(hrmJdCodeQueryRequest.getCodeType())) {
            throw new UnavailableException("CodeType 必填");
        }
        if (Objects.isNull(hrmJdCodeQueryRequest.getValue())) {
            throw new UnavailableException("value 必填");
        }

        HrmJdCodeEntity entity = QueryChain.of(HrmJdCodeEntity.class)
                .select(HrmJdCodeEntity::getCode, HrmJdCodeEntity::getValue)
                .eq(HrmJdCodeEntity::getCodeType, hrmJdCodeQueryRequest.getCodeType())
                .eq(HrmJdCodeEntity::getValue, hrmJdCodeQueryRequest.getValue(), Objects.nonNull(hrmJdCodeQueryRequest.getValue()))
                .eq(HrmJdCodeEntity::getAppId, AppContext.getAppId())
                .eq(HrmJdCodeEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                .one(); // 查询单条
        JdCodeResponse response = null;
        if (entity != null) {
            response = JdCodeResponse.builder()
                    .code(entity.getCode())
                    .value(entity.getValue())
                    .codeType(entity.getCodeType())
                    .build();
        }
    return response;

    }

    @Operation(summary = "保存岗位规则")
    public OrgRuleResponse saveOrgRule(OrgRuleSaveRequest orgRuleSaveRequest) {
        OrgRuleResponse response = new OrgRuleResponse();
        OrgRuleEntity objEntity = new OrgRuleEntity();
        BeanUtils.copyProperties(orgRuleSaveRequest, objEntity, OrgRuleEntity.class);
        objEntity.setTs(new Date());
        objEntity.setOperatorId(aacContext.getAacUser().getUserId());
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
        objEntity.setOperatorId(aacContext.getAacUser().getUserId());
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
        if (Objects.nonNull(orgIndicatorListSaveRequest) && Objects.nonNull(orgIndicatorListSaveRequest.getIndicatorList())) {
            //先删除
            if (orgIndicatorListSaveRequest.getIndicatorList().size() > 0) {
                Long ruleId = orgIndicatorListSaveRequest.getIndicatorList().get(0).getOrgRuleId();
                int deletedRows = orgIndicatorService.deleteByOrgRuleId(ruleId);
                log.debug("delete records " + deletedRows);
            }
            //重新保存
            for (OrgIndicatorSaveRequest itemEntity : orgIndicatorListSaveRequest.getIndicatorList()) {
                OrgIndicatorEntity objEntity = new OrgIndicatorEntity();
                BeanUtils.copyProperties(itemEntity, objEntity, OrgIndicatorEntity.class);
                objEntity.setTs(new Date());
                objEntity.setOperatorId(aacContext.getAacUser().getUserId());
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



    public JDInfoResponse queryJdInfo(Long orgJdId) throws JsonProcessingException {
        String cacheKey = "jd:detail:id:" + orgJdId;
        log.info("queryJdInfo_cacheKey:{}", cacheKey);
        JDInfoResponse jdInfoResponse = radeCache.get(cacheKey,JDInfoResponse.class);
        log.info("queryJdInfo_jdInfoResponse1:{}", jdInfoResponse);
        if (jdInfoResponse== null || jdInfoResponse.getOrgjdId() == null) {
            jdInfoResponse = queryJdInfoFromDb(orgJdId);
            log.info("queryJdInfo_jdInfoResponse2:{}", jdInfoResponse);

        }
        return jdInfoResponse;
    }

    @Operation(summary = "存储JD内容")
    public Response saveOrgJd(JDSaveRequest saveRequest) throws UnavailableException, JsonProcessingException {
        CompanyInfo companyInfo =saveRequest.getCompanyInfo();
        Integer scale = null;
        if(StringUtils.isNotBlank(companyInfo.getScale())){
            scale = CompanyScaleEnum.getCodeByDescription(companyInfo.getScale());
        }
        Integer fundingStage = null;
        if(StringUtils.isNotBlank(companyInfo.getFundingStage())){
            fundingStage = FinancingStageEnum.getCodeByDescription(companyInfo.getFundingStage());
        }
        HrmJdCodeQueryRequest hrmJdCodeQueryRequest = new HrmJdCodeQueryRequest();
        hrmJdCodeQueryRequest.setCodeType("projectType");
        hrmJdCodeQueryRequest.setValue(companyInfo.getProjectType());
        JdCodeResponse jdCodeResponse = getJdCodeOne(hrmJdCodeQueryRequest);
        Long enterpriseSituationId = companyInfo.getHrmEnterpriseSituationId();
        Integer projectType = jdCodeResponse.getCode();
        Integer projectProgress = ProjectProgressEnum.getCodeByDescription(companyInfo.getProjectProgress());
        if(!Objects.isNull(enterpriseSituationId)){
            HrmEnterpriseSituationEntity situationEntity = QueryChain.of(HrmEnterpriseSituationEntity.class)
                    .select()
                    .eq(HrmEnterpriseSituationEntity::getAppId,AppContext.getAppId())
                    .eq(HrmEnterpriseSituationEntity::getDeleted,CommonDelEnum.NORMAL.getCode())
                    .one();
            if(Objects.nonNull(situationEntity)){
                Map<ChangeField, String> changes = new HashMap<>();

                // 比较并记录变更字段
                if (!Objects.equals(situationEntity.getCompanyScale(), scale)) {
                    changes.put(ChangeField.SCALE, companyInfo.getScale());
                }
                if (!Objects.equals(situationEntity.getFinancingStage(), fundingStage)) {
                    changes.put(ChangeField.FUNDING_STAGE, companyInfo.getFundingStage());
                }
                if (!Objects.equals(situationEntity.getProjectType(), projectType)) {
                    changes.put(ChangeField.PROJECT_TYPE, companyInfo.getProjectType());
                }
                if (!Objects.equals(situationEntity.getProjectProgress(), projectProgress)) {
                    changes.put(ChangeField.PROJECT_PROGRESS, companyInfo.getProjectProgress());
                }
                if (!Objects.equals(situationEntity.getSimilarPositions(), companyInfo.getSimilarPositions())) {
                    changes.put(ChangeField.SIMILAR_POSITIONS, companyInfo.getSimilarPositions());
                }
                //3. 如果有变更，创建新记录
                if (!changes.isEmpty()) {
                    // 创建新实体（使用Builder模式）
                    HrmEnterpriseSituationEntity newEntity = HrmEnterpriseSituationEntity.builder()
                            // 设置变更字段
                            .companyScale(scale)
                            .financingStage(fundingStage)
                            .projectType(projectType)
                            .projectProgress(projectProgress)
                            .similarPositions(companyInfo.getSimilarPositions())

                            // 继承未变更字段
                            .appId(AppContext.getAppId())
                            .deleted(0)
                            .ownerId(aacContext.getAacUser().getUserId())

                            // 设置变更关系
                            .oldEnterpriseSituationId(enterpriseSituationId)

                            // 设置变更备注
                            .remark(generateChangeRemark(changes, situationEntity))

                            // 时间戳
                            .ts(new Date())
                            .ut(new Date())
                            .build();
                    newEntity.save();
                    enterpriseSituationId = newEntity.getHrmEnterpriseSituationId();
                }
            }else {

                // 创建新实体（使用Builder模式）
                enterpriseSituationId = saveJd(scale,fundingStage,projectType,projectProgress,companyInfo.getSimilarPositions());
            }
        }else {
            enterpriseSituationId = saveJd(scale,fundingStage,projectType,projectProgress,companyInfo.getSimilarPositions());
        }
        SalaryBenefitInfo salaryBenefitInfo = saveRequest.getSalaryBenefitInfo();

        HrmFeatureBenefitsEntity benefitsEntity = new HrmFeatureBenefitsEntity();

        benefitsEntity.setMonthlySalaryRange(MonthlySalaryRangeEnum.getCodeByDescription(salaryBenefitInfo.getMonthlySalaryRange()));
        benefitsEntity.setWorkMode(WorkModeEnum.getCodeByDescription(salaryBenefitInfo.getWorkMode()));
        if(CollectionUtil.isNotEmpty(salaryBenefitInfo.getCoreBenefits())){
            benefitsEntity.setBenefit(salaryBenefitInfo.getCoreBenefits().stream().map(CoreBenefitsEnum::getCodeByDescription).map(String::valueOf)                             // 转为字符串
                    .collect(Collectors.joining(",")));
            salaryBenefitInfo.getCoreBenefits().forEach(description -> {
                String benefitUstomize = CoreBenefitsEnum.getUstomizeByDescription(description);
                if(StringUtils.isNotBlank(benefitUstomize)){
                    benefitsEntity.setBenefitUstomize(benefitUstomize);
                }
            });
        }
        if(CollectionUtil.isNotEmpty(salaryBenefitInfo.getTeamFeatures())){
            benefitsEntity.setFeature(salaryBenefitInfo.getTeamFeatures().stream().map(TeamFeaturesEnum::getCodeByDescription).map(String::valueOf)                             // 转为字符串
                    .collect(Collectors.joining(",")));
            salaryBenefitInfo.getTeamFeatures().forEach(description -> {
                String featureUstomize = TeamFeaturesEnum.getUstomizeByDescription(description);
                if(StringUtils.isNotBlank(featureUstomize)){
                    benefitsEntity.setFeatureUstomize(featureUstomize);
                }
            });
        }
        benefitsEntity.setAppId(AppContext.getAppId());
        benefitsEntity.setDeleted(0);
        benefitsEntity.setTs(new Date());
        benefitsEntity.setUt(new Date());
        benefitsEntity.setOwnerId(aacContext.getAacUser().getUserId());
        benefitsEntity.save();

        OrgJdEntity jdEntity = new OrgJdEntity();
        jdEntity.setOrgRootId(aacContext.getAacUser().getOrgRootId());
        jdEntity.setOrgTreeId(aacContext.getAacUser().getOrgTreeId());
        jdEntity.setOrgAddress(companyInfo.getOrgAddress());
        jdEntity.setOrgJdCategoryId(saveRequest.getOrgJdCategoryId());
        if(Objects.nonNull(saveRequest.getOwnerId())) {
            jdEntity.setOwnerId(saveRequest.getOwnerId());
        }
        jdEntity.setOperatorId(aacContext.getAacUser().getUserId());
        jdEntity.setJdNo("JD"+UUID.randomUUID().toString().replace("-", ""));
        jdEntity.setJdName(saveRequest.getBasicInfo().getJdName());
        //jdEntity.setGender(GenderRequirementEnum.getCodeByDescription(saveRequest.getBasicInfo().getGenderRequirement()));
        jdEntity.setAgeRange(AgeRangeEnum.getCodeByDescription(saveRequest.getBasicInfo().getAgeRange()));
        jdEntity.setWorkExper(WorkExperienceEnum.getCodeByDescription(saveRequest.getBasicInfo().getExperienceRequirement()));
        jdEntity.setMinEducation(EducationRequirementEnum.getCodeByDescription(saveRequest.getBasicInfo().getEducationRequirement()));
        jdEntity.setRecruitmentPurpose(saveRequest.getBasicInfo().getRecruitmentPurpose().stream().map(RecruitmentPurposeEnum::getCodeByDescription) // 直接通过描述获取 code
                .map(String::valueOf)                             // 转为字符串
                .collect(Collectors.joining(",")));
        if(StringUtils.isNotBlank(saveRequest.getOtherRequirements())){
            jdEntity.setOtherRequire(saveRequest.getOtherRequirements());
        }
        if(StringUtils.isNotBlank(saveRequest.getJdRequire().getLanguageRequirements())){
            jdEntity.setLanguageRequirements(saveRequest.getJdRequire().getLanguageRequirements());
        }
        if (StringUtils.isNotBlank(saveRequest.getJdRequire().getTechStack())) {
            jdEntity.setTechStack(saveRequest.getJdRequire().getTechStack());
        }
        jdEntity.setEnterpriseSituationId(enterpriseSituationId);
        jdEntity.setHrmFeatureBenefitsId(benefitsEntity.getHrmFeatureBenefitsId());
        jdEntity.setDescription(saveRequest.getRequirements());
        jdEntity.setState(1);
        jdEntity.setAppId(AppContext.getAppId());
        jdEntity.setTs(new Date());
        jdEntity.setUt(new Date());
        jdEntity.save();
        // 用前缀隔离 key
        String cacheKey = "jd:detail:id:" + jdEntity.getOrgJdId();
        saveRequest.setOrgjdId(jdEntity.getOrgJdId());
        saveRequest.setJdNo(jdEntity.getJdNo());
        saveRequest.getSalaryBenefitInfo().setHrmFeatureBenefitsId(benefitsEntity.getHrmFeatureBenefitsId());
        radeCache.set(cacheKey, objectMapper.writeValueAsString(saveRequest));
        return Response.ok();
    }

    @Operation(summary ="JD详情列表")
    public List<JDInfoResponse> queryJdInfoList(List<Long> orgJdIds){
        List<JDInfoResponse> jdList = new ArrayList<>();
        orgJdIds.forEach(orgJdId -> {
            JDInfoResponse response = null;
            try {
                response = queryJdInfo(orgJdId);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
            jdList.add(response);
        });
        return jdList;
    }

    @Transactional
    public Response updateJdInfo(JDSaveRequest saveRequest) throws JsonProcessingException {
        String cacheKey = "jd:detail:id:" + saveRequest.getOrgjdId();
        JDInfoResponse jdInfoResponse = radeCache.get(cacheKey,JDInfoResponse.class);
        if (jdInfoResponse== null || jdInfoResponse.getOrgjdId() == null) {
            jdInfoResponse = queryJdInfoFromDb(saveRequest.getOrgjdId());
            if(jdInfoResponse == null){
                return Response.failed("JD详情不存在");
            }
        }


        OrgJdEntity jdEntity = null;
        if(!saveRequest.getBasicInfo().getJdName().equals(jdInfoResponse.getBasicInfo().getJdName())){
            jdEntity = new OrgJdEntity();
            jdEntity.setJdName(saveRequest.getBasicInfo().getJdName());
        }
        if (saveRequest.getOrgJdCategoryId() != jdInfoResponse.getOrgJdCategoryId()){
            if (jdEntity == null) {
                jdEntity = new OrgJdEntity();
            }
            jdEntity.setOrgJdCategoryId(saveRequest.getOrgJdCategoryId());
        }

        if(saveRequest.getOwnerId() != jdInfoResponse.getOwnerId()){
            if (jdEntity == null) {
                jdEntity = new OrgJdEntity();
            }
            jdEntity.setOwnerId(saveRequest.getOwnerId());
        }

        if(!saveRequest.getRequirements().equals(jdInfoResponse.getRequirements())){
            if (jdEntity == null) {
                jdEntity = new OrgJdEntity();
            }
            jdEntity.setDescription(saveRequest.getRequirements());
        }
        if(!saveRequest.getBasicInfo().getEducationRequirement().equals(jdInfoResponse.getBasicInfo().getEducationRequirement())){
            if (jdEntity == null) {
                jdEntity = new OrgJdEntity();
            }
            jdEntity.setMinEducation(EducationRequirementEnum.getCodeByDescription(saveRequest.getBasicInfo().getEducationRequirement()));
        }

        if(!saveRequest.getBasicInfo().getExperienceRequirement().equals(jdInfoResponse.getBasicInfo().getExperienceRequirement())){
            if (jdEntity == null) {
                jdEntity = new OrgJdEntity();
            }
            jdEntity.setWorkExper(WorkExperienceEnum.getCodeByDescription(saveRequest.getBasicInfo().getExperienceRequirement()));
        }

        if (StringUtils.isNotBlank(saveRequest.getJdRequire().getTechStack())) {
            if (!saveRequest.getJdRequire().getTechStack().equals(jdInfoResponse.getJdRequire().getTechStack())) {
                if (jdEntity == null) {
                    jdEntity = new OrgJdEntity();
                }
                jdEntity.setTechStack(saveRequest.getJdRequire().getTechStack());
            }

        }
        if(StringUtils.isNotBlank(saveRequest.getJdRequire().getLanguageRequirements())){
            if(!saveRequest.getJdRequire().getLanguageRequirements().equals(jdInfoResponse.getJdRequire().getLanguageRequirements())){
                if (jdEntity == null) {
                    jdEntity = new OrgJdEntity();
                }
                jdEntity.setLanguageRequirements(saveRequest.getJdRequire().getLanguageRequirements());
            }

        }

        if(StringUtils.isNotBlank(saveRequest.getOtherRequirements())){
            if(!saveRequest.getOtherRequirements().equals(jdInfoResponse.getOtherRequirements())){
                if (jdEntity == null) {
                    jdEntity = new OrgJdEntity();
                }
                jdEntity.setOtherRequire(saveRequest.getOtherRequirements());
            }

        }
        if(jdEntity != null){
            jdEntity.setOrgJdId(saveRequest.getOrgjdId());
            jdEntity.setUt(new Date());
            jdEntity.setOperatorId(aacContext.getAacUser().getUserId());
            jdEntity.updateById();
            if (!saveRequest.getSalaryBenefitInfo().getMonthlySalaryRange().equals(jdInfoResponse.getSalaryBenefitInfo().getMonthlySalaryRange())) {
                HrmFeatureBenefitsEntity benefitsEntity = new HrmFeatureBenefitsEntity();
                benefitsEntity.setHrmFeatureBenefitsId(saveRequest.getSalaryBenefitInfo().getHrmFeatureBenefitsId());
                benefitsEntity.setUt(new Date());
                benefitsEntity.setOwnerId(aacContext.getAacUser().getUserId());
                benefitsEntity.setMonthlySalaryRange(MonthlySalaryRangeEnum.getCodeByDescription(saveRequest.getSalaryBenefitInfo().getMonthlySalaryRange()));
                benefitsEntity.updateById();
                radeCache.set(cacheKey, objectMapper.writeValueAsString(saveRequest));
                return Response.ok();
            }
            radeCache.set(cacheKey, objectMapper.writeValueAsString(saveRequest));
            return Response.ok();

        }else {
            if (!saveRequest.getSalaryBenefitInfo().getMonthlySalaryRange().equals(jdInfoResponse.getSalaryBenefitInfo().getMonthlySalaryRange())) {
                HrmFeatureBenefitsEntity benefitsEntity = new HrmFeatureBenefitsEntity();
                benefitsEntity.setHrmFeatureBenefitsId(saveRequest.getSalaryBenefitInfo().getHrmFeatureBenefitsId());
                benefitsEntity.setUt(new Date());
                benefitsEntity.setOwnerId(aacContext.getAacUser().getUserId());
                benefitsEntity.setMonthlySalaryRange(MonthlySalaryRangeEnum.getCodeByDescription(saveRequest.getSalaryBenefitInfo().getMonthlySalaryRange()));
                benefitsEntity.updateById();
                radeCache.set(cacheKey, objectMapper.writeValueAsString(saveRequest));
                return Response.ok();
            }else {
                return Response.failed("未修改请勿提交");
            }
        }


    }


    private JDInfoResponse queryJdInfoFromDb(Long orgJdId) throws JsonProcessingException {
        JDInfoResponse response = null;

        OrgJdEntity jdEntity = QueryChain.of(OrgJdEntity.class)
                .eq(OrgJdEntity::getOrgJdId,orgJdId)
                .eq(OrgJdEntity::getDeleted, CommonDelEnum.NORMAL.getCode())
                .one();
        if(Objects.nonNull(jdEntity)){
            response = new JDInfoResponse();
            response.setOrgjdId(orgJdId);
            // jdEntity.setOrgAddress(companyInfo.getOrgAddress());
            response.getCompanyInfo().setOrgAddress(jdEntity.getOrgAddress());
            //jdEntity.setOrgJdCategoryId(saveRequest.getOrgJdCategoryId());
            response.setOrgJdCategoryId(jdEntity.getOrgJdCategoryId());
            if (Objects.nonNull(jdEntity.getOwnerId())) {
                response.setOwnerId(jdEntity.getOwnerId());
            }
            // jdEntity.setJdNo("JD"+UUID.randomUUID().toString().replace("-", ""));
            response.setJdNo(jdEntity.getJdNo());
            // jdEntity.setJdName(saveRequest.getBasicInfo().getJdName());
            response.getBasicInfo().setJdName(jdEntity.getJdName());
            //jdEntity.setAgeRange(AgeRangeEnum.getCodeByDescription(saveRequest.getBasicInfo().getAgeRange()));
            response.getBasicInfo().setAgeRange(AgeRangeEnum.getByCode(jdEntity.getAgeRange()).getDescription());
            // jdEntity.setWorkExper(WorkExperienceEnum.getCodeByDescription(saveRequest.getBasicInfo().getExperienceRequirement()));
            response.getBasicInfo().setExperienceRequirement(WorkExperienceEnum.getByCode(jdEntity.getWorkExper()).getDescription());
            // jdEntity.setMinEducation(EducationRequirementEnum.getCodeByDescription(saveRequest.getBasicInfo().getEducationRequirement()));
            response.getBasicInfo().setEducationRequirement(EducationRequirementEnum.getByCode(jdEntity.getMinEducation()).getDescription());
//            jdEntity.setRecruitmentPurpose(saveRequest.getBasicInfo().getRecruitmentPurpose().stream().map(RecruitmentPurposeEnum::getCodeByDescription) // 直接通过描述获取 code
//                    .map(String::valueOf)                             // 转为字符串
//                    .collect(Collectors.joining(",")));
            String recruitmentPurposeStr = jdEntity.getRecruitmentPurpose();
            List<Integer> recruitmentPurposeList = recruitmentPurposeStr == null ? Collections.emptyList() : Arrays.stream(recruitmentPurposeStr.split(","))
                    .filter(StringUtils::isNotBlank)
                    .map(Integer::parseInt)
                    .toList();
            List<String> purposeEnums = recruitmentPurposeList.stream()
                    .map(code -> {
                        try {
                            return RecruitmentPurposeEnum.getByCode(code).getDescription();
                        } catch (IllegalArgumentException e) {
                            // 记录无效 code（可选）
                            log.warn("无效的 recruitmentPurpose code: {}", code);
                            return null;
                        }
                    })
                    .filter(Objects::nonNull)
                    .toList();
            response.getBasicInfo().setRecruitmentPurpose(purposeEnums);

//            if(StringUtils.isNotBlank(saveRequest.getOtherRequirements())){
//                jdEntity.setOtherRequire(saveRequest.getOtherRequirements());
//            }
            if(StringUtils.isNotBlank(jdEntity.getOtherRequire())){
                response.setOtherRequirements(jdEntity.getOtherRequire());
            }
//            if(StringUtils.isNotBlank(saveRequest.getJdRequire().getLanguageRequirements())){
//                jdEntity.setLanguageRequirements(saveRequest.getJdRequire().getLanguageRequirements());
//            }
            if(StringUtils.isNotBlank(jdEntity.getLanguageRequirements())){
                response.getJdRequire().setLanguageRequirements(jdEntity.getLanguageRequirements());
            }
           /* if (StringUtils.isNotBlank(saveRequest.getJdRequire().getTechStack())) {
                jdEntity.setTechStack(saveRequest.getJdRequire().getTechStack());
            }*/
            if (StringUtils.isNotBlank(jdEntity.getTechStack())){
                response.getJdRequire().setTechStack(jdEntity.getTechStack());
            }
            response.setRequirements(jdEntity.getDescription());
            HrmEnterpriseSituationEntity situationEntity = QueryChain.of(HrmEnterpriseSituationEntity.class)
                    .eq(HrmEnterpriseSituationEntity::getHrmEnterpriseSituationId,jdEntity.getEnterpriseSituationId())
                    .eq(HrmEnterpriseSituationEntity::getDeleted,CommonDelEnum.NORMAL.getCode())
                    .eq(HrmEnterpriseSituationEntity::getAppId,jdEntity.getAppId())
                    .one();
            if(Objects.nonNull(situationEntity.getCompanyScale())){
                response.getCompanyInfo().setScale(CompanyScaleEnum.getByCode(situationEntity.getCompanyScale()).getDescription());
            }
            if(Objects.nonNull(situationEntity.getFinancingStage())){
                response.getCompanyInfo().setFundingStage(FinancingStageEnum.getByCode(situationEntity.getFinancingStage()).getDescription());
            }
            response.getCompanyInfo().setHrmEnterpriseSituationId(jdEntity.getEnterpriseSituationId());
            response.getCompanyInfo().setProjectType(getProjectTypeDescription(situationEntity.getProjectType()));
            response.getCompanyInfo().setProjectProgress(ProjectProgressEnum.getByCode(situationEntity.getProjectProgress()).getDescription());
            response.getCompanyInfo().setSimilarPositions(situationEntity.getSimilarPositions());
            HrmFeatureBenefitsEntity benefitsEntity = QueryChain.of(HrmFeatureBenefitsEntity.class)
                    .eq(HrmFeatureBenefitsEntity::getHrmFeatureBenefitsId,jdEntity.getHrmFeatureBenefitsId())
                    .eq(HrmFeatureBenefitsEntity::getDeleted,CommonDelEnum.NORMAL.getCode())
                    .eq(HrmFeatureBenefitsEntity::getAppId,jdEntity.getAppId())
                    .one();
            response.getSalaryBenefitInfo().setMonthlySalaryRange(MonthlySalaryRangeEnum.getByCode(benefitsEntity.getMonthlySalaryRange()).getDescription());
            response.getSalaryBenefitInfo().setWorkMode(WorkModeEnum.getByCode(benefitsEntity.getWorkMode()).getDescription());
            if(StringUtils.isNotBlank(benefitsEntity.getBenefit())){
                String benefitsStr = benefitsEntity.getBenefit();
                List<Integer> benefits = Arrays.stream(benefitsStr.split(","))
                        .filter(StringUtils::isNotBlank)
                        .map(Integer::parseInt)
                        .toList();
                List<String> benefitDescs = benefits.stream()
                        .map(code -> {
                            try {
                                if(code ==6){
                                    return benefitsEntity.getBenefitUstomize();
                                }
                                return CoreBenefitsEnum.getByCode(code).getDescription();
                            } catch (IllegalArgumentException e) {
                                // 记录无效 code（可选）
                                log.warn("无效的 CoreBenefitsEnum code: {}", code);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .toList();
                response.getSalaryBenefitInfo().setCoreBenefits(benefitDescs);
            }
            if(StringUtils.isNotBlank(benefitsEntity.getFeature())){
                String featuresStr = benefitsEntity.getFeature();
                List<Integer> features = Arrays.stream(featuresStr.split(","))
                        .filter(StringUtils::isNotBlank)
                        .map(Integer::parseInt)
                        .toList();
                List<String> benefitDescs = features.stream()
                        .map(code -> {
                            try {
                                if(code ==5){
                                    return benefitsEntity.getFeatureUstomize();
                                }
                                return TeamFeaturesEnum.getByCode(code).getDescription();
                            } catch (IllegalArgumentException e) {
                                // 记录无效 code（可选）
                                log.warn("无效的 TeamFeaturesEnum code: {}", code);
                                return null;
                            }
                        })
                        .filter(Objects::nonNull)
                        .toList();
                response.getSalaryBenefitInfo().setCoreBenefits(benefitDescs);
            }
            response.getSalaryBenefitInfo().setHrmFeatureBenefitsId(benefitsEntity.getHrmFeatureBenefitsId());
            String cacheKey = "jd:detail:id:" + response.getOrgjdId();
            radeCache.set(cacheKey, objectMapper.writeValueAsString(response));
            return response;

        }else {
            return response;
        }

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

    public List<OrgJdOrgRegisterInfoListResponse> getOrgJdOrgRegisterInfoList(OrgJdOrgRegisterInfoListRequest request) {
        if (Objects.isNull(request) || CollectionUtil.isEmpty(request.getOrgJdIds())) {
            return null;
        }
        List<OrgJdEntity> jdEntities = QueryChain.of(OrgJdEntity.class)
                .in(OrgJdEntity::getOrgJdId, request.getOrgJdIds().stream().distinct().collect(Collectors.toList()))
                .list();
        if (CollectionUtil.isEmpty(jdEntities)) {
            return null;
        }
        if (!Objects.equals(request.getQueryOrgRegisterInfo(), true)) {
            return BeanUtil.copyToList(jdEntities, OrgJdOrgRegisterInfoListResponse.class);
        }
        Map<Long, OrgRegisterEntity> orgRegisterEntityMap = QueryChain.of(OrgRegisterEntity.class)
                .in(OrgRegisterEntity::getOrgRootId, jdEntities.stream().map(OrgJdEntity::getOrgRootId).collect(Collectors.toSet()))
                .list().stream().collect(Collectors.toMap(OrgRegisterEntity::getOrgRootId, Function.identity(), (l, r) -> l));
        return jdEntities.stream().map(record -> {
            OrgJdOrgRegisterInfoListResponse jdAndOrgDetailListResponse = new OrgJdOrgRegisterInfoListResponse();
            BeanUtils.copyProperties(record, jdAndOrgDetailListResponse);
            if (orgRegisterEntityMap.containsKey(record.getOrgRootId())) {
                OrgJdOrgRegisterInfoListResponse.OrgRegisterInfo orgRegisterInfo = new OrgJdOrgRegisterInfoListResponse.OrgRegisterInfo();
                BeanUtils.copyProperties(orgRegisterEntityMap.get(record.getOrgRootId()), orgRegisterInfo);
                jdAndOrgDetailListResponse.setOrgRegisterInfo(orgRegisterInfo);
            }
            return jdAndOrgDetailListResponse;
        }).collect(Collectors.toList());
    }

    public OrgRegisterResponse getContextRootOrg() {
        OrgRegisterEntity orgRegisterEntity = orgRegisterService.getOne(QueryWrapper.create()
                .eq(OrgRegisterEntity::getOrgRootId, aacContext.getAacUser().getOrgRootId()));
        return BeanUtil.copyProperties(orgRegisterEntity, OrgRegisterResponse.class);
    }
}
