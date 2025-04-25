package org.dows.uim.open;

import com.mybatisflex.core.paginate.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.dows.uim.biz.HrAccountApiBiz;
import org.dows.uim.biz.InterviewBiz;
import org.dows.uim.request.AccountInstanceRequest;
import org.dows.uim.request.HrAccountInstanceRequest;
import org.dows.uim.response.HrAccountInstanceResponse;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/open/uim/admin/recruiter")
@Tag(name = "后台管理招聘官管理", description = "后台管理招聘官管理")
@RequiredArgsConstructor
public class OrgRecruiterRest {
    private final HrAccountApiBiz hrAccountApiBiz;
//    /**
//     * 新增招聘官
//     *
//     */
//    @Operation(summary = "新增招聘官")
//    @PostMapping("/add")
//    public void add(@RequestBody @Validated AdminJdEditFO reqFo) {
//        // todo 登录用户组织id
//        Long orgRootId = null;
//        orgJdBiz.jdUpDown(orgRootId, reqFo);
//    }

    /**
     * 分页列表
     * @param request 分页参数
     * @return records
     */
    @GetMapping("/page")
    public Page<HrAccountInstanceResponse> page(HrAccountInstanceRequest request) {
        return hrAccountApiBiz.page(request);
    }

//    /**
//     * 编辑修改
//     *
//     */
//    @Operation(summary = "编辑修改")
//    @PostMapping("/edit")
//    public void edit(@RequestBody @Validated AdminJdEditFO reqFo) {
//        // todo 登录用户组织id
//        Long orgRootId = null;
//        orgJdBiz.jdUpDown(orgRootId, reqFo);
//    }
//    /**
//     * 查看详情信息
//     */
//    @Operation(summary = "查看详情信息")
//    @GetMapping("/info")
//    public AdminTalentDetailVO info(@RequestParam("orgJdId") Long orgJdId) {
//        // todo 登录用户组织id
//        Long orgRootId = null;
//        return orgJdBiz.getJdInfo(orgRootId, orgJdId);
//    }
//
    /**
     * 删除
     *
     */
    @Operation(summary = "删除")
    @PostMapping("/del")
    public Boolean del(@RequestBody @Validated AccountInstanceRequest accountInstanceRequest) {
        // todo 登录用户组织id
        Long orgRootId = null;
        return hrAccountApiBiz.delete(accountInstanceRequest.getAccountInstanceId());
    }


}
