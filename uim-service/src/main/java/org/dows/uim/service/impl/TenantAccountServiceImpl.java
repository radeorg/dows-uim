package org.dows.uim.service.impl;

import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.TenantAccountEntity;
import org.dows.uim.mapper.TenantAccountMapper;
import org.dows.uim.service.TenantAccountService;
import org.springframework.stereotype.Service;

/**
 * 租户账号表(TenantAccount)表服务实现类
 *
 * @author lait.zhang@gmail.com
 * @since 2025-08-10 17:51:17
 */
@Service("tenantAccountService")
public class TenantAccountServiceImpl  extends BaseServiceImpl<TenantAccountMapper, TenantAccountEntity> implements TenantAccountService {
}
