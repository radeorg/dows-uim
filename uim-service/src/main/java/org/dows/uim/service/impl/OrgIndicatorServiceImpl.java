package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.OrgIndicatorService;
import org.dows.uim.entity.OrgIndicatorEntity;
import org.dows.uim.mapper.OrgIndicatorMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 岗位指标表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class OrgIndicatorServiceImpl extends ServiceImpl<OrgIndicatorMapper, OrgIndicatorEntity> implements OrgIndicatorService {

}