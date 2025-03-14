package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.OrgKnowledgeService;
import org.dows.uim.entity.OrgKnowledgeEntity;
import org.dows.uim.mapper.OrgKnowledgeMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 岗位知识表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class OrgKnowledgeServiceImpl extends ServiceImpl<OrgKnowledgeMapper, OrgKnowledgeEntity> implements OrgKnowledgeService {

}