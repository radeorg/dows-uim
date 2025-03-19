package org.dows.uim.service.impl;


import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.AccountInstanceEntity;
import org.dows.uim.mapper.AccountInstanceMapper;
import org.dows.uim.service.AccountInstanceService;
import org.springframework.stereotype.Service;

/**
 * 账号实例表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class AccountInstanceServiceImpl extends BaseServiceImpl<AccountInstanceMapper, AccountInstanceEntity> implements AccountInstanceService {

}