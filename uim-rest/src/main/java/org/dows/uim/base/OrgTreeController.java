package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.annotation.RadeController;
import org.dows.rade.crud.BaseController;
import org.dows.uim.entity.OrgTreeEntity;
import org.dows.uim.service.OrgTreeService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 组织树表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/orgTree")
@Tag(name = "组织树表控制层")
public class OrgTreeController extends BaseController<OrgTreeService, OrgTreeEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}