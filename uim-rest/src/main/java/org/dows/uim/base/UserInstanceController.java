package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.crud.BaseController;
import org.dows.rade.web.RadeController;
import org.dows.uim.entity.UserInstanceEntity;
import org.dows.uim.service.UserInstanceService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户实例表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/userInstance")
@Tag(name = "用户实例表控制层")
public class UserInstanceController extends BaseController<UserInstanceService, UserInstanceEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}