package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.UserCerticationService;
import org.dows.uim.entity.UserCerticationEntity;
import org.dows.uim.mapper.UserCerticationMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 用户证书表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class UserCerticationServiceImpl extends ServiceImpl<UserCerticationMapper, UserCerticationEntity> implements UserCerticationService {

}