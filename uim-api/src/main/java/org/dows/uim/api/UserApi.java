package org.dows.uim.api;

import org.dows.uim.api.request.UserInfoRequest;

public interface UserApi {
    Long saveUserInfo(UserInfoRequest user);

}
