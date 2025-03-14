package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.OrgNodeService;
import org.dows.uim.entity.OrgNodeEntity;
import org.dows.uim.mapper.OrgNodeMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 组织节点表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class OrgNodeServiceImpl extends ServiceImpl<OrgNodeMapper, OrgNodeEntity> implements OrgNodeService {

}