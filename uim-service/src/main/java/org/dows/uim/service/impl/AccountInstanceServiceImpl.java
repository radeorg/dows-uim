package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.AccountInstanceService;
import org.dows.uim.entity.AccountInstanceEntity;
import org.dows.uim.mapper.AccountInstanceMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 账号实例表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class AccountInstanceServiceImpl extends ServiceImpl<AccountInstanceMapper, AccountInstanceEntity> implements AccountInstanceService {

}