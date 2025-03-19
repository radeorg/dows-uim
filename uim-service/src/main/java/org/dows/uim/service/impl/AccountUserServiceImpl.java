package org.dows.uim.service.impl;


import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.AccountUserEntity;
import org.dows.uim.mapper.AccountUserMapper;
import org.dows.uim.service.AccountUserService;
import org.springframework.stereotype.Service;

/**
 * 账号用户表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class AccountUserServiceImpl extends BaseServiceImpl<AccountUserMapper, AccountUserEntity> implements AccountUserService {

}