package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dows.rade.crud.BaseController;
import org.dows.rade.web.RadeController;
import org.dows.uim.api.request.AccountInstanceRequest;
import org.dows.uim.biz.AccountApiBiz;
import org.dows.uim.entity.AccountInstanceEntity;
import org.dows.uim.service.AccountInstanceService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号实例表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@Slf4j
@RequiredArgsConstructor
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/accountInstance")
@Tag(name = "账号实例表控制层")
public class AccountInstanceController extends BaseController<AccountInstanceService, AccountInstanceEntity>  {

    private final AccountApiBiz accountApiBiz;
    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

    public Long accountRegister(AccountInstanceRequest accountInstance) {
        return accountApiBiz.accountRegister(accountInstance);
    }
}