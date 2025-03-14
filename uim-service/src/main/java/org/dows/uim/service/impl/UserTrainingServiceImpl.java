package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.UserTrainingService;
import org.dows.uim.entity.UserTrainingEntity;
import org.dows.uim.mapper.UserTrainingMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 用户培训表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserTrainingServiceImpl extends ServiceImpl<UserTrainingMapper, UserTrainingEntity> implements UserTrainingService {

}