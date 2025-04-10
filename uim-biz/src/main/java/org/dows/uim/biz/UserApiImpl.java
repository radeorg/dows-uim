package org.dows.uim.biz;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.uim.api.UserApi;
import org.dows.uim.request.UserInfoRequest;
import org.dows.uim.entity.UserInstanceEntity;
import org.dows.uim.service.UserInstanceService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserApiImpl implements UserApi {
    private final UserInstanceService userInstanceService;

    @Override
    public Long saveUserInfo(UserInfoRequest user) {
        UserInstanceEntity userInstance = new UserInstanceEntity();
        BeanUtils.copyProperties(user, userInstance);
        userInstanceService.save(userInstance);

        //用户实例Id
        return  userInstance.getUserInstanceId();
    }
}
