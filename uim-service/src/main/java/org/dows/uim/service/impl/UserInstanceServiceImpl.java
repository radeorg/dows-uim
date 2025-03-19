package org.dows.uim.service.impl;


import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.UserInstanceEntity;
import org.dows.uim.mapper.UserInstanceMapper;
import org.dows.uim.service.UserInstanceService;
import org.springframework.stereotype.Service;

/**
 * 用户实例表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserInstanceServiceImpl extends BaseServiceImpl<UserInstanceMapper, UserInstanceEntity> implements UserInstanceService {

}