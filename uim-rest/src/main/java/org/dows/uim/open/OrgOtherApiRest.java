package org.dows.uim.open;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.UnavailableException;
import lombok.RequiredArgsConstructor;
import org.dows.uim.api.OrgOtherApi;
import org.dows.uim.biz.OrgOtherApiBiz;
import org.dows.uim.request.OrgAddressRequest;
import org.dows.uim.response.OrgAddressResponse;
import org.dows.uim.response.OrgJdcategoryResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("/open/org")
@Tag(name = "组织管理接口", description = "组织管理接口")
@RequiredArgsConstructor
public class OrgOtherApiRest implements OrgOtherApi {
    private final OrgOtherApiBiz orgOtherApiBiz;

    @Operation(summary = "获取地址列表")
    public List<OrgAddressResponse> getOrgAddressList(@RequestParam Long orgRootId) throws UnavailableException {
        return orgOtherApiBiz.getOrgAddressList(orgRootId);
    }

    @Operation(summary = "保存地址信息")
    public OrgAddressResponse saveOrgAddress(@RequestBody OrgAddressRequest orgAddressRequest) throws UnavailableException {
        return orgOtherApiBiz.saveOrgAddress(orgAddressRequest);
    }

    @Operation(summary = "删除某个地址")
    public Boolean deleteOrgAddress(Long orgAddressId) throws UnavailableException {
        return orgOtherApiBiz.deleteOrgAddress(orgAddressId);
    }

    @Operation(summary = "获取职位分类列表")
    public List<OrgJdcategoryResponse> getOrgJdcategoryList(@RequestParam Long orgRootId) throws UnavailableException {
        return orgOtherApiBiz.getOrgJdcategoryList(orgRootId);
    }
}

