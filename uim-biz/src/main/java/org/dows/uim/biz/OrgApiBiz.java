package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
import org.dows.rade.aac.AacContext;
import org.dows.rade.constant.IdentifierType;
import org.dows.rade.encrypt.EncryptApi;
import org.dows.rade.util.DateUtil;
import org.dows.uim.constant.CommonDelEnum;
import org.dows.uim.entity.*;
import org.dows.uim.exception.UimException;
import org.dows.uim.request.*;
import org.dows.uim.response.*;
import org.dows.uim.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

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
        if (Objects.isNull(request) || CollectionUtils.isEmpty(request.getOrgJdIds())) {
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
