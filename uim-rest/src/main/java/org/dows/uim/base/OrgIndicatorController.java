package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.annotation.RadeController;
import org.dows.rade.crud.BaseController;
import org.dows.uim.entity.OrgIndicatorEntity;
import org.dows.uim.service.OrgIndicatorService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 岗位指标表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/orgIndicator")
@Tag(name = "岗位指标表控制层")
public class OrgIndicatorController extends BaseController<OrgIndicatorService, OrgIndicatorEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}