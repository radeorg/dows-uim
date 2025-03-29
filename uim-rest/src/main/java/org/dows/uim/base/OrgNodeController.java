package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.crud.BaseController;
import org.dows.rade.web.RadeController;
import org.dows.uim.entity.OrgNodeEntity;
import org.dows.uim.service.OrgNodeService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 组织节点表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/orgNode")
@Tag(name = "组织节点表控制层")
public class OrgNodeController extends BaseController<OrgNodeService, OrgNodeEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}