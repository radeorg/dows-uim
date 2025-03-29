package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.crud.BaseController;
import org.dows.rade.web.RadeController;
import org.dows.uim.entity.AccountTypeEntity;
import org.dows.uim.service.AccountTypeService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号类型表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/accountType")
@Tag(name = "账号类型表控制层")
public class AccountTypeController extends BaseController<AccountTypeService, AccountTypeEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}