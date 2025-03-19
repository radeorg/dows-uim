package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.annotation.RadeController;
import org.dows.rade.crud.BaseController;
import org.dows.uim.entity.AccountInstanceEntity;
import org.dows.uim.service.AccountInstanceService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号实例表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/accountInstance")
@Tag(name = "账号实例表控制层")
public class AccountInstanceController extends BaseController<AccountInstanceService, AccountInstanceEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}