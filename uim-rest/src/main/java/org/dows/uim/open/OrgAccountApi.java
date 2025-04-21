package org.dows.uim.open;

import org.dows.uim.request.AddOrgAccountRequest;
import org.dows.uim.response.AddOrgAccountResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrgAccountApi {
    @PostMapping("/v1/open/uim/org/account/add")
    default List<AddOrgAccountResponse> saveOrgAccount(@RequestBody List<AddOrgAccountRequest> addOrgAccountRequests) {
        throw new UnsupportedOperationException();
    }
}
