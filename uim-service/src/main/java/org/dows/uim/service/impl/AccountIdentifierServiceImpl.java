package org.dows.uim.service.impl;


import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.AccountIdentifierEntity;
import org.dows.uim.mapper.AccountIdentifierMapper;
import org.dows.uim.service.AccountIdentifierService;
import org.springframework.stereotype.Service;

/**
 * 账号标识表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class AccountIdentifierServiceImpl extends BaseServiceImpl<AccountIdentifierMapper, AccountIdentifierEntity> implements AccountIdentifierService {

}