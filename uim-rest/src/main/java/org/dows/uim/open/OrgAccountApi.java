package org.dows.uim.open;

import org.dows.uim.request.SaveOrgAccountRequest;
import org.dows.uim.response.SaveOrgAccountResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface OrgAccountApi {
    @PostMapping("/v1/open/uim/org/account/add")
    default List<SaveOrgAccountResponse> saveOrgAccount(@RequestBody List<SaveOrgAccountRequest> saveOrgAccountRequests) {
        throw new UnsupportedOperationException();
    }
}
