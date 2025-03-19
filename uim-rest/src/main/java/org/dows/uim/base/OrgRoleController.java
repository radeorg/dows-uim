package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.annotation.RadeController;
import org.dows.rade.crud.BaseController;
import org.dows.uim.entity.OrgRoleEntity;
import org.dows.uim.service.OrgRoleService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 组织角色表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/orgRole")
@Tag(name = "组织角色表控制层")
public class OrgRoleController extends BaseController<OrgRoleService, OrgRoleEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}