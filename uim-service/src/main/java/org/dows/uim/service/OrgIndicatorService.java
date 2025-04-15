package org.dows.uim.service;


import org.dows.rade.crud.BaseService;
import org.dows.uim.entity.OrgIndicatorEntity;


/**
 * 岗位指标表 服务层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
public interface OrgIndicatorService extends BaseService<OrgIndicatorEntity> {
    int deleteByOrgRuleId(Long orgRuleId);
}