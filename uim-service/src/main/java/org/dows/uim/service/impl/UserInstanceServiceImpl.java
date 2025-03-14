package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.UserInstanceService;
import org.dows.uim.entity.UserInstanceEntity;
import org.dows.uim.mapper.UserInstanceMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 用户实例表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserInstanceServiceImpl extends ServiceImpl<UserInstanceMapper, UserInstanceEntity> implements UserInstanceService {

}