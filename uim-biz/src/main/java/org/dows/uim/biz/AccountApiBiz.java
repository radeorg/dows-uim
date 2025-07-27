package org.dows.uim.biz;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.mybatisflex.core.query.QueryChain;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.constant.IdentifierType;
import org.dows.rade.crud.AppIdIgnoreUtils;
import org.dows.uim.api.AccountTypeRequest;
import org.dows.uim.api.AccountTypeResponse;
import org.dows.uim.entity.AccountIdentifierEntity;
import org.dows.uim.entity.AccountInstanceEntity;
import org.dows.uim.entity.AccountRoleEntity;
import org.dows.uim.entity.AccountTypeEntity;
import org.dows.uim.exception.UimException;
import org.dows.uim.handler.AccountHandler;
import org.dows.uim.request.*;
import org.dows.uim.request.FindAccountIdentifierRequest;
import org.dows.uim.response.*;
import org.dows.uim.service.AccountIdentifierService;
import org.dows.uim.service.AccountInstanceService;
import org.dows.uim.service.AccountTypeService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@Slf4j
@RequiredArgsConstructor
@Component
public class AccountApiBiz {
    private final AccountInstanceService accountInstanceService;
    private final AccountIdentifierService accountIdentifierService;
    private final AccountTypeService accountTypeService;


    private final AccountHandler accountHandler;

    /**
     * @param accountInstance
     * @return
     */
    @Transactional
    public Long accountRegister(AccountInstanceRequest accountInstance) {
        // 保存账号 实例
        AccountInstanceEntity accountInstanceEntity =
                BeanUtil.copyProperties(accountInstance, AccountInstanceEntity.class);
        accountInstanceService.save(accountInstanceEntity);
        Long accountInstanceId = accountInstanceEntity.getAccountInstanceId();
        // 保存账号 标识
        AccountIdentifierEntity accountIdentifierEntity = new AccountIdentifierEntity();
        accountIdentifierEntity.setAccountInstanceId(accountInstanceId);
        accountIdentifierEntity.setIdentifier(accountInstance.getIdentifier());
        // fix #2023-04-09 账号标识类型
        accountIdentifierEntity.setIdentifierType(accountInstance.getIdentifierType());
//        accountIdentifierEntity.setAppId(accountInstance.getAppId());
        accountIdentifierEntity.setOperatorId(accountInstanceId);
        accountIdentifierService.save(accountIdentifierEntity);
        return accountInstanceId;
    }

    public AccountInstanceResponse getAccountInstanceByAccountName(String appId, String accountName) {
        AccountInstanceResponse response = new AccountInstanceResponse();

        List<AccountIdentifierEntity> accountIdentifierEntityList = QueryChain.of(AccountIdentifierEntity.class)
                .eq(AccountIdentifierEntity::getIdentifier, accountName, Objects.nonNull(accountName))
                .eq(AccountIdentifierEntity::getAppId, appId, Objects.nonNull(appId)).list();
        if (Objects.isNull(accountIdentifierEntityList) || accountIdentifierEntityList.isEmpty()) {
            return response;
        }

        Long accountInstanceId = accountIdentifierEntityList.get(0).getAccountInstanceId();

        AccountInstanceEntity accountInstanceEntity = QueryChain.of(AccountInstanceEntity.class)
                .eq(AccountInstanceEntity::getAccountInstanceId, accountInstanceId, Objects.nonNull(accountInstanceId))
                .eq(AccountInstanceEntity::getAppId, appId, Objects.nonNull(appId)).one();
        if (Objects.isNull(accountInstanceEntity)) {
            return response;
        }

        BeanUtil.copyProperties(accountInstanceEntity, response);
        response.setIdentifier(accountInstanceEntity.getTelephone());
        response.setSuperAccount(true);

        return response;
    }

    public AccountIdentifierResponse getAccountIdentifier(FindAccountIdentifierRequest findAccountIdentifierRequest) {
        AccountIdentifierEntity[] identifierHolder = new AccountIdentifierEntity[1];
        AppIdIgnoreUtils.executeWithoutTenant(() -> {
            identifierHolder[0] = accountIdentifierService.getOne(QueryWrapper.create()
                    .eq(AccountIdentifierEntity::getAccountInstanceId, findAccountIdentifierRequest.getAccountInstanceId(), Objects.nonNull(findAccountIdentifierRequest.getAccountInstanceId()))
                    .eq(AccountIdentifierEntity::getIdentifier, findAccountIdentifierRequest.getIdentifier())
                    .eq(AccountIdentifierEntity::getIdentifierType, findAccountIdentifierRequest.getIdentifierType().getType()));
        });
        return BeanUtil.copyProperties(identifierHolder[0], AccountIdentifierResponse.class);
    }


    public AccountInstanceResponse getAccountInstanceById(Long accountInstanceId) {
        AccountInstanceEntity one = accountInstanceService.getById(accountInstanceId);
        return BeanUtil.copyProperties(one, AccountInstanceResponse.class);
    }


    /**
     * @param appId
     * @param accountInstanceId
     * @return
     */
    public List<Long> getAllRoleIds(String appId, Long accountInstanceId) {
        List<Long> roleList = new ArrayList<>();

        List<AccountRoleEntity> accountRoleEntityList = QueryChain.of(AccountRoleEntity.class)
                .eq(AccountRoleEntity::getAppId, appId, Objects.nonNull(appId))
                .eq(AccountRoleEntity::getAccountInstanceId, accountInstanceId, Objects.nonNull(accountInstanceId)).list();
        if (Objects.isNull(accountRoleEntityList) || accountRoleEntityList.isEmpty()) {
            return roleList;
        }
        for (AccountRoleEntity item : accountRoleEntityList) {
            roleList.add(item.getRbacRoleId());
        }

        return roleList;
    }

    /**
     * @param accountIdentifier 账号标识
     * @return AccountInstanceResponse
     */
    public AccountInstanceResponse getAccountInstanceByIdentifier(String accountIdentifier) {
        AccountIdentifierEntity accountIdentifierEntity = getByIdentifierAndIgnoreAppId(accountIdentifier);
        if (accountIdentifierEntity == null) {
            return null;
        }

        Long accountInstanceId = accountIdentifierEntity.getAccountInstanceId();
        if (accountInstanceId == null) {
            log.debug("accountInstanceId is null");
            return null;
        }

        AccountInstanceEntity accountInstanceEntity = getByAccountInstanceIdAndIgnoreAppId(accountInstanceId);
        if (Objects.isNull(accountInstanceEntity)) {
            return null;
        }
        AccountInstanceResponse accountInstanceResponse = BeanUtil
                .copyProperties(accountInstanceEntity, AccountInstanceResponse.class);
        accountInstanceResponse.setIdentifier(accountIdentifier);
        IdentifierType byIdentifierType = IdentifierType
                .getByIdentifierType(accountIdentifierEntity.getIdentifierType());
        accountInstanceResponse.setIdentifierType(byIdentifierType);
        return accountInstanceResponse;
    }

    public AccountOrgIdsResponse getOrgIdsByAccountId(String appId, Long accountInstanceId, boolean check) {
        AccountOrgIdsResponse response = new AccountOrgIdsResponse();

        return response;
    }

    public List<AccountRoleRelationResponse> getRoleByAccountInstanceId(String appId, List<Long> principals) {
        List<AccountRoleRelationResponse> response = new ArrayList<>();

        return List.of();
    }

    @Transactional
    public void saveOrgAccount(SaveOrgAccountRequest saveOrgAccountRequest)  {
        accountHandler.saveOrgAccount(saveOrgAccountRequest);
    }

    /**
     * 获取账号类型
     *
     * @param accountTypeRequest
     * @return
     */
    public List<AccountTypeResponse> getAccountType(AccountTypeRequest accountTypeRequest) {
        List<AccountTypeEntity> list = accountTypeService.list(QueryWrapper.create()
                .eq(AccountTypeEntity::getAccountInstanceId, accountTypeRequest.getAccountInstanceId()));
        return BeanUtil.copyToList(list, AccountTypeResponse.class);
    }

    /**
     * 验证并绑定账号信息（更具手机号和账号ID验证，如果存在则绑定，不存在则抛出异常）
     *
     * @param bindingAccountRequest
     */
    @Transactional
    public void bindingAccount(BindingAccountRequest bindingAccountRequest) {

        Long accountInstanceId = bindingAccountRequest.getAccountInstanceId();
        // 构建查询条件，根据账号实例ID查询账号实例
        /*QueryWrapper eq = QueryWrapper.create()
                .eq(AccountInstanceEntity::getAccountInstanceId, accountInstanceId);*/
        // 查询账号标识（手机号）是否存在
        QueryWrapper eq = QueryWrapper.create()
                .eq(AccountIdentifierEntity::getAccountInstanceId, accountInstanceId)
                .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.PHONE.getType())
                .eq(AccountIdentifierEntity::getIdentifier, bindingAccountRequest.getTelephone());
        AccountIdentifierEntity identifierEntity = accountIdentifierService.getOne(eq);
        if (identifierEntity == null) {
            throw new UimException("手机号验证失败，请检查系统注册手机号是否为当前手机号");
        }
        // 绑定时，根据条件绑定，校验字段值是否存在且相等
        List<String> verifiers = bindingAccountRequest.getVerifiers();
        if (verifiers != null && !verifiers.isEmpty()) {
            for (String verifier : verifiers) {
                Object fieldValue = BeanUtil.getFieldValue(identifierEntity, verifier);
                Object fieldValue1 = BeanUtil.getFieldValue(bindingAccountRequest, verifier);
                if (!StrUtil.equals(fieldValue.toString(), fieldValue1.toString())) {
                    throw new UimException(String.format("UIM账号绑定失败，字段或值不匹配，字段：%s，值：%s", verifier, fieldValue1));
                }
            }
            AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
            if (StrUtil.isNotEmpty(bindingAccountRequest.getNickname())) {
                accountInstanceEntity.setNickname(bindingAccountRequest.getNickname());
            }
            if (StrUtil.isNotEmpty(bindingAccountRequest.getAvatar())) {
                accountInstanceEntity.setAvatar(bindingAccountRequest.getAvatar());
            }
            /*if(StrUtil.isNotEmpty(bindingAccountRequest.getTelephone())){
                accountInstanceEntity.setTelephone(bindingAccountRequest.getTelephone());
            }*/
            if (StrUtil.isNotEmpty(bindingAccountRequest.getZoneNo())) {
                accountInstanceEntity.setZoneNo(bindingAccountRequest.getZoneNo());
            }
            // 根据查询条件accountId&appId更新账号信息到账号实例,实现更新账号实例信息，完成绑定账号信息，比如手机号绑定，以及其他字段信息绑定
            accountInstanceService.update(accountInstanceEntity, eq);
           /* if (one == null) {
            // 保存账号标识
            accountIdentifierService.save(AccountIdentifierEntity.builder()
                    .accountInstanceId(accountInstanceId)
                    .identifier(bindingAccountRequest.getTelephone())
                    .identifierType(IdentifierType.PHONE.getType())
                    .build());
            }*/
        }
    }

    @Transactional
    public Long getAccountByTelephone(String telephone) {
        // 查询账号标识（手机号）是否存在
        AccountIdentifierEntity one = accountIdentifierService.getOne(QueryWrapper.create()
                //.eq(AccountIdentifierEntity::getAppId, appId, Objects.nonNull(appId))
                .eq(AccountIdentifierEntity::getIdentifier, telephone, Objects.nonNull(telephone)));
        // 存在则返回账号实例ID，不存在则创建账号实例并返回账号实例ID
        if (one != null) {
            return one.getAccountInstanceId();
        }
        // 创建账号实例
        AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
        accountInstanceEntity.setTelephone(telephone);
        accountInstanceService.save(accountInstanceEntity);
        // 保存账号标识;
        AccountIdentifierEntity identifierEntity = new AccountIdentifierEntity();
        identifierEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
        identifierEntity.setIdentifier(telephone);
        identifierEntity.setIdentifierType(IdentifierType.PHONE.getType());
        accountIdentifierService.save(identifierEntity);
        return accountInstanceEntity.getAccountInstanceId();
    }

    @Transactional
    public Long getAccountByTelephone(String telephone,String email) {
        // 查询账号标识（手机号）是否存在
        AccountIdentifierEntity onePhone = accountIdentifierService.getOne(QueryWrapper.create()
                .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.PHONE.getType())
                .eq(AccountIdentifierEntity::getIdentifier, telephone, Objects.nonNull(telephone)));
        // 存在则返回账号实例ID，不存在则创建账号实例并返回账号实例ID
        if (onePhone != null) {
            AccountIdentifierEntity oneEmail = accountIdentifierService.getOne(QueryWrapper.create()
                    .eq(AccountIdentifierEntity::getIdentifierType, IdentifierType.EMAIL.getType())
                    .eq(AccountIdentifierEntity::getIdentifier, email, Objects.nonNull(email)));
            if (oneEmail == null) {
            // 保存账号标识;
                AccountIdentifierEntity oneEmailEntity = new AccountIdentifierEntity();
                oneEmailEntity.setAccountInstanceId(onePhone.getAccountInstanceId());
                oneEmailEntity.setIdentifier(email);
                oneEmailEntity.setIdentifierType(IdentifierType.EMAIL.getType());
                accountIdentifierService.save(oneEmailEntity);
            }

            return onePhone.getAccountInstanceId();

        }
        // 创建账号实例
        AccountInstanceEntity accountInstanceEntity = new AccountInstanceEntity();
        accountInstanceEntity.setTelephone(telephone);
        accountInstanceService.save(accountInstanceEntity);
        // 保存账号标识;
        AccountIdentifierEntity telephoneEntity = new AccountIdentifierEntity();
        telephoneEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
        telephoneEntity.setIdentifier(telephone);
        telephoneEntity.setIdentifierType(IdentifierType.PHONE.getType());
        accountIdentifierService.save(telephoneEntity);
        AccountIdentifierEntity emailEntity = new AccountIdentifierEntity();
        emailEntity.setAccountInstanceId(accountInstanceEntity.getAccountInstanceId());
        emailEntity.setIdentifier(email);
        emailEntity.setIdentifierType(IdentifierType.EMAIL.getType());
        accountIdentifierService.save(emailEntity);
        return accountInstanceEntity.getAccountInstanceId();
    }

    /**
     * 根据账号ID列表获取账号实例列表
     *
     * @param appId
     * @param accountIds
     * @param filters
     * @return
     */
    public List<AccountInstanceResponse> getAccountInstanceByIds(String appId, List<Long> accountIds, List<String> filters) {
        List<AccountInstanceEntity> accountInstanceEntities = accountInstanceService.list(QueryWrapper.create()
                .select(filters.toArray(new String[0]))
                .in(AccountInstanceEntity::getAccountInstanceId, accountIds, Objects.nonNull(accountIds))
                .eq(AccountInstanceEntity::getAppId, appId, Objects.nonNull(appId)));
        return BeanUtil.copyToList(accountInstanceEntities, AccountInstanceResponse.class);
    }

    /**
     * 保存账号标识，如果存在则返回账号标识ID，不存在则创建账号标识并返回账号标识ID
     *
     * @param identifier
     * @param identifierType
     * @return
     */
    public AccountIdentifierResponse saveAccountIdentifier(String identifier, IdentifierType identifierType) {

        AccountIdentifierEntity one = getByIdentifierAndIgnoreAppId(identifier, identifierType.getType());
        // 存在则返回账号标识ID，不存在则创建账号标识并返回账号标识ID
        if (one != null) {
            return BeanUtil.copyProperties(one, AccountIdentifierResponse.class);
        }
        // 保存账号标识
        one = new AccountIdentifierEntity();
        one.setIdentifier(identifier);
        one.setIdentifierType(identifierType.getType());
        accountIdentifierService.save(one);
        return BeanUtil.copyProperties(one, AccountIdentifierResponse.class);
    }

    /**
     * 根据手机号查询账号实例ID并关联到openid账号标识上
     *
     * @param relevancyAccountInstanceIdByTelephoneRequest
     */
    public void relevancyAccountInstanceIdForOpenidByTelephone(RelevancyAccountInstanceIdForOpenidByTelephoneRequest
                                                                       relevancyAccountInstanceIdByTelephoneRequest) {

        AccountIdentifierEntity one = new AccountIdentifierEntity();
        one.setAccountIdentifierId(relevancyAccountInstanceIdByTelephoneRequest.getAccountIdentifierId());
        one.setAccountInstanceId(relevancyAccountInstanceIdByTelephoneRequest.getAccountInstanceId());
        one.setOperatorId(relevancyAccountInstanceIdByTelephoneRequest.getAccountInstanceId());
        accountIdentifierService.updateById(one, true);

    }
    /**
     * 根据账号实例ID修改账号密码
     * @param accountInstanceId 账号实例ID
     * @param newPassword 新密码
     */
    public void updateInstancePasswordByAccountInstanceId(Long accountInstanceId, String newPassword) {
        AccountInstanceEntity one = accountInstanceService.getById(accountInstanceId);
        if (one != null) {
            one.setPassword(newPassword);
            accountInstanceService.updateById(one, true);
        }
    }

    /**
     * 不带appId查询
     */
    private AccountIdentifierEntity getByIdentifierAndIgnoreAppId(String identifier) {
        AtomicReference<AccountIdentifierEntity> holder = new AtomicReference<>();
        AppIdIgnoreUtils.executeWithoutTenant(() -> {
            holder.set(accountIdentifierService.getOne(QueryWrapper.create()
                    .eq(AccountIdentifierEntity::getIdentifier, identifier)));
        });
        return holder.get();
    }

    /**
     * 不带appId查询
     */
    private AccountIdentifierEntity getByIdentifierAndIgnoreAppId(String identifier, Integer identifierType) {
        AtomicReference<AccountIdentifierEntity> holder = new AtomicReference<>();
        AppIdIgnoreUtils.executeWithoutTenant(() -> {
            AccountIdentifierEntity one = accountIdentifierService.getOne(QueryWrapper.create()
                    .eq(AccountIdentifierEntity::getIdentifier, identifier)
                    .eq(AccountIdentifierEntity::getIdentifierType, identifierType));
            holder.set(one);
        });
        return holder.get();
    }

    /**
     * 不带appId查询
     */
    private AccountInstanceEntity getByAccountInstanceIdAndIgnoreAppId(Long accountInstanceId) {
        AtomicReference<AccountInstanceEntity> holder = new AtomicReference<>();
        AppIdIgnoreUtils.executeWithoutTenant(() -> {
            holder.set(accountInstanceService.getById(accountInstanceId));
        });
        return holder.get();
    }
}
