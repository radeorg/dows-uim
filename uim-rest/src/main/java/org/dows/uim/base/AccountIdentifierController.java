package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.annotation.RadeController;
import org.dows.rade.crud.BaseController;
import org.dows.uim.entity.AccountIdentifierEntity;
import org.dows.uim.service.AccountIdentifierService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号标识表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add"})
@RequestMapping("/accountIdentifier")
@Tag(name = "账号标识表控制层")
public class AccountIdentifierController extends BaseController<AccountIdentifierService, AccountIdentifierEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}