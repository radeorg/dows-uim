package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.annotation.RadeController;
import org.dows.rade.crud.BaseController;
import org.dows.uim.entity.AccountRelationEntity;
import org.dows.uim.service.AccountRelationService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 账号推荐人表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/accountRelation")
@Tag(name = "账号推荐人表控制层")
public class AccountRelationController extends BaseController<AccountRelationService, AccountRelationEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}