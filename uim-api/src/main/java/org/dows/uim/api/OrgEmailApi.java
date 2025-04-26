package org.dows.uim.api;


import jakarta.servlet.UnavailableException;
import org.dows.uim.response.OrgEmailResponse;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @ClassName OrgEmailApi
 * @Description TODO
 * @Author jack.china.ye
 * @Date 2025/4/24 20:23
 */

public interface OrgEmailApi {

    @PostMapping("/v1/uim/open/email/get")
    default OrgEmailResponse getEmailInfo(Long rootId) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }
}
