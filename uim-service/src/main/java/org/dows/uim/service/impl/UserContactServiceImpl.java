package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.UserContactService;
import org.dows.uim.entity.UserContactEntity;
import org.dows.uim.mapper.UserContactMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 用户联系人表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserContactServiceImpl extends ServiceImpl<UserContactMapper, UserContactEntity> implements UserContactService {

}