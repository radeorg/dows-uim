package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.annotation.RadeController;
import org.dows.rade.crud.BaseController;
import org.dows.uim.entity.UserCerticationEntity;
import org.dows.uim.service.UserCerticationService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用户证书表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/userCertication")
@Tag(name = "用户证书表控制层")
public class UserCerticationController extends BaseController<UserCerticationService, UserCerticationEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}