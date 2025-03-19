package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.annotation.RadeController;
import org.dows.rade.crud.BaseController;
import org.dows.uim.entity.OrgRuleEntity;
import org.dows.uim.service.OrgRuleService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 岗位规则表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/orgRule")
@Tag(name = "岗位规则表控制层")
public class OrgRuleController extends BaseController<OrgRuleService, OrgRuleEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}