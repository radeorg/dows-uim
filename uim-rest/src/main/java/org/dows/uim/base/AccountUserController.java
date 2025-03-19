package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.annotation.RadeController;
import org.dows.rade.crud.BaseController;
import org.dows.uim.entity.AccountUserEntity;
import org.dows.uim.service.AccountUserService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号用户表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/accountUser")
@Tag(name = "账号用户表控制层")
public class AccountUserController extends BaseController<AccountUserService, AccountUserEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}