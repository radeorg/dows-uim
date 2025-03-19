package org.dows.uim.base;

import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.dows.rade.annotation.RadeController;
import org.dows.rade.crud.BaseController;
import org.dows.uim.entity.OrgKnowledgeEntity;
import org.dows.uim.service.OrgKnowledgeService;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 岗位知识表 控制层。
 *
 * @author lait.zhang@gmail.com
 * @since 1.0
 */
@RadeController(api = {"add", "delete", "update", "page", "info"})
@RequestMapping("/orgKnowledge")
@Tag(name = "岗位知识表控制层")
public class OrgKnowledgeController extends BaseController<OrgKnowledgeService, OrgKnowledgeEntity> {

    @Override
    protected void init(HttpServletRequest request, JSONObject requestParams) {
    }

}