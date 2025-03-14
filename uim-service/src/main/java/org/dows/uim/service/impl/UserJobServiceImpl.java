package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.UserJobService;
import org.dows.uim.entity.UserJobEntity;
import org.dows.uim.mapper.UserJobMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 用户工作表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserJobServiceImpl extends ServiceImpl<UserJobMapper, UserJobEntity> implements UserJobService {

}