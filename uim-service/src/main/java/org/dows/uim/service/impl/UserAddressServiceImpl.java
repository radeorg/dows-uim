package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.UserAddressService;
import org.dows.uim.entity.UserAddressEntity;
import org.dows.uim.mapper.UserAddressMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 用户地址表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserAddressServiceImpl extends ServiceImpl<UserAddressMapper, UserAddressEntity> implements UserAddressService {

}