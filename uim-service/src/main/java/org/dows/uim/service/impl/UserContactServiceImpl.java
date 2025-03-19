package org.dows.uim.service.impl;


import org.dows.rade.crud.BaseServiceImpl;
import org.dows.uim.entity.UserContactEntity;
import org.dows.uim.mapper.UserContactMapper;
import org.dows.uim.service.UserContactService;
import org.springframework.stereotype.Service;

/**
 * 用户联系人表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserContactServiceImpl extends BaseServiceImpl<UserContactMapper, UserContactEntity> implements UserContactService {

}