package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.OrgTreeService;
import org.dows.uim.entity.OrgTreeEntity;
import org.dows.uim.mapper.OrgTreeMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 组织树表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class OrgTreeServiceImpl extends ServiceImpl<OrgTreeMapper, OrgTreeEntity> implements OrgTreeService {

}