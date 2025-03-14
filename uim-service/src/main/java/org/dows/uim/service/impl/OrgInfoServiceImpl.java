package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.OrgInfoService;
import org.dows.uim.entity.OrgInfoEntity;
import org.dows.uim.mapper.OrgInfoMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 组织信息表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class OrgInfoServiceImpl extends ServiceImpl<OrgInfoMapper, OrgInfoEntity> implements OrgInfoService {

}