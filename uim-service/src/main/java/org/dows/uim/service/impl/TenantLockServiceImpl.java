package org.dows.uim.service.impl;

import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.TenantLockEntity;
import org.dows.uim.mapper.TenantLockMapper;
import org.dows.uim.service.TenantLockService;
import org.springframework.stereotype.Service;

/**
 * 租户锁(TenantLock)表服务实现类
 *
 * @author lait.zhang@gmail.com
 * @since 2025-08-10 17:51:19
 */
@Service("tenantLockService")
public class TenantLockServiceImpl extends BaseServiceImpl<TenantLockMapper, TenantLockEntity> implements TenantLockService {
}
