package org.dows.uim.service.impl;


import org.springframework.stereotype.Service;
import org.dows.uim.service.AccountIdentifierService;
import org.dows.uim.entity.AccountIdentifierEntity;
import org.dows.uim.mapper.AccountIdentifierMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;

/**
 * 账号标识表 服务层实现。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Service
public class AccountIdentifierServiceImpl extends ServiceImpl<AccountIdentifierMapper, AccountIdentifierEntity> implements AccountIdentifierService {

    public void dd(){
        this.mapper.dd();
    }
}