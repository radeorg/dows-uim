package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.crud.BaseController;
import org.dows.rade.web.RadeController;
import org.dows.uim.entity.UserJobEntity;
import org.dows.uim.service.UserJobService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户工作表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/userJob")
@Tag(name = "用户工作表控制层")
public class UserJobController extends BaseController<UserJobService, UserJobEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}