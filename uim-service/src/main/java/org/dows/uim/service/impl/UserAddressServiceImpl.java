package org.dows.uim.service.impl;


import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.UserAddressEntity;
import org.dows.uim.mapper.UserAddressMapper;
import org.dows.uim.service.UserAddressService;
import org.springframework.stereotype.Service;

/**
 * 用户地址表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserAddressServiceImpl extends BaseServiceImpl<UserAddressMapper, UserAddressEntity> implements UserAddressService {

}