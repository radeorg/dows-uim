package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.crud.BaseController;
import org.dows.rade.web.RadeController;
import org.dows.uim.entity.AccountRoleEntity;
import org.dows.uim.service.AccountRoleService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号角色表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/accountRole")
@Tag(name = "账号角色表控制层")
public class AccountRoleController extends BaseController<AccountRoleService, AccountRoleEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}