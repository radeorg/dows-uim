package org.dows.uim.open;

import org.dows.uim.request.SaveOrgAccountRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface OrgAccountApi {
    @PostMapping("/v1/open/uim/org/account/add")
    default void saveOrgAccount(@RequestBody SaveOrgAccountRequest saveOrgAccountRequests) {
        throw new UnsupportedOperationException();
    }
}
