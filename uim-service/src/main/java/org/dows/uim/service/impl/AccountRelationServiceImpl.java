package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.AccountRelationService;
import org.dows.uim.entity.AccountRelationEntity;
import org.dows.uim.mapper.AccountRelationMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 账号推荐人表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class AccountRelationServiceImpl extends ServiceImpl<AccountRelationMapper, AccountRelationEntity> implements AccountRelationService {

}