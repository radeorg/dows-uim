package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.AccountUserService;
import org.dows.uim.entity.AccountUserEntity;
import org.dows.uim.mapper.AccountUserMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 账号用户表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class AccountUserServiceImpl extends ServiceImpl<AccountUserMapper, AccountUserEntity> implements AccountUserService {

}