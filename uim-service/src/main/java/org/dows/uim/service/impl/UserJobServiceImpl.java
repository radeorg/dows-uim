package org.dows.uim.service.impl;


import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.UserJobEntity;
import org.dows.uim.mapper.UserJobMapper;
import org.dows.uim.service.UserJobService;
import org.springframework.stereotype.Service;

/**
 * 用户工作表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserJobServiceImpl extends BaseServiceImpl<UserJobMapper, UserJobEntity> implements UserJobService {

}