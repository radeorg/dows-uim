package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.UserEducationService;
import org.dows.uim.entity.UserEducationEntity;
import org.dows.uim.mapper.UserEducationMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 用户教育表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserEducationServiceImpl extends ServiceImpl<UserEducationMapper, UserEducationEntity> implements UserEducationService {

}