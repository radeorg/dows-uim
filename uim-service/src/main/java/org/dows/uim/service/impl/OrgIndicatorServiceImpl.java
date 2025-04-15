package org.dows.uim.service.impl;


import com.mybatisflex.core.query.QueryWrapper;
import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.OrgIndicatorEntity;
import org.dows.uim.mapper.OrgIndicatorMapper;
import org.dows.uim.service.OrgIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 岗位指标表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class OrgIndicatorServiceImpl extends BaseServiceImpl<OrgIndicatorMapper, OrgIndicatorEntity> implements OrgIndicatorService {
    @Autowired
    private OrgIndicatorMapper orgIndicatorMapper;

    /**
     * 根据 OrgRuleId 删除记录
     * @param orgRuleId 组织规则ID
     * @return 删除的行数
     */
    public int deleteByOrgRuleId(Long orgRuleId) {
        QueryWrapper query = QueryWrapper.create()
                .where("org_rule_id = " + String.valueOf(orgRuleId));

        return orgIndicatorMapper.deleteByQuery(query);
    }
}