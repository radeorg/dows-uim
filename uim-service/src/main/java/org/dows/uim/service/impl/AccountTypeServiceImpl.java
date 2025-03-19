package org.dows.uim.service.impl;


import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.AccountTypeEntity;
import org.dows.uim.mapper.AccountTypeMapper;
import org.dows.uim.service.AccountTypeService;
import org.springframework.stereotype.Service;

/**
 * 账号类型表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class AccountTypeServiceImpl extends BaseServiceImpl<AccountTypeMapper, AccountTypeEntity> implements AccountTypeService {

}