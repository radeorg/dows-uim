package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.crud.BaseController;
import org.dows.rade.web.RadeController;
import org.dows.uim.entity.UserFamilyEntity;
import org.dows.uim.service.UserFamilyService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户家庭表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/userFamily")
@Tag(name = "用户家庭表控制层")
public class UserFamilyController extends BaseController<UserFamilyService, UserFamilyEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}