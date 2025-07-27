package org.dows.uim.service.impl;


import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.TenantInstanceEntity;
import org.dows.uim.mapper.TenantInstanceMapper;
import org.dows.uim.service.TenantInstanceService;
import org.springframework.stereotype.Service;

/**
 * 租户实例表 服务层实现。
 *
 */
@Service
public class TenantInstanceServiceImpl extends BaseServiceImpl<TenantInstanceMapper, TenantInstanceEntity> implements TenantInstanceService {

}