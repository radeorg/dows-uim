package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.AccountRoleService;
import org.dows.uim.entity.AccountRoleEntity;
import org.dows.uim.mapper.AccountRoleMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 账号角色表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class AccountRoleServiceImpl extends ServiceImpl<AccountRoleMapper, AccountRoleEntity> implements AccountRoleService {

}