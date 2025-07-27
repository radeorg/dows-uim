package org.dows.uim.service.impl;


import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.TenantAppEntity;
import org.dows.uim.mapper.TenantAppMapper;
import org.dows.uim.service.TenantAppService;
import org.springframework.stereotype.Service;

/**
 * 租户应用表 服务层实现。
 *
 */
@Service
public class TenantAppServiceImpl extends BaseServiceImpl<TenantAppMapper, TenantAppEntity> implements TenantAppService {

}