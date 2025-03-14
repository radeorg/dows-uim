package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.AccountTypeService;
import org.dows.uim.entity.AccountTypeEntity;
import org.dows.uim.mapper.AccountTypeMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 账号类型表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class AccountTypeServiceImpl extends ServiceImpl<AccountTypeMapper, AccountTypeEntity> implements AccountTypeService {

}