package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.OrgRuleService;
import org.dows.uim.entity.OrgRuleEntity;
import org.dows.uim.mapper.OrgRuleMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 岗位规则表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class OrgRuleServiceImpl extends ServiceImpl<OrgRuleMapper, OrgRuleEntity> implements OrgRuleService {

}