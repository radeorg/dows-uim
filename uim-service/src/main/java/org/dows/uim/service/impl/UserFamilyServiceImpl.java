package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.UserFamilyService;
import org.dows.uim.entity.UserFamilyEntity;
import org.dows.uim.mapper.UserFamilyMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 用户家庭表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserFamilyServiceImpl extends ServiceImpl<UserFamilyMapper, UserFamilyEntity> implements UserFamilyService {

}