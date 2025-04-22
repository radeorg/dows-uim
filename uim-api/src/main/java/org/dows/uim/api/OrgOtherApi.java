package org.dows.uim.api;

import jakarta.servlet.UnavailableException;
import org.dows.uim.request.OrgAddressRequest;
import org.dows.uim.response.OrgAddressResponse;
import org.dows.uim.response.OrgJdcategoryResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public interface OrgOtherApi {

    @GetMapping("/v1/uim/org/address/list")
    default List<OrgAddressResponse> getOrgAddressList(Long orgRootId) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @PostMapping("/v1/uim/org/address/save")
    default OrgAddressResponse saveOrgAddress(OrgAddressRequest orgAddressRequest) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @DeleteMapping("/v1/uim/org/address/delete")
    default Boolean deleteOrgAddress(Long orgAddressId) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }

    @GetMapping("/v1/uim/org/jdcategory/list")
    default List<OrgJdcategoryResponse> getOrgJdcategoryList(Long orgRootId) throws UnavailableException {
        throw new UnsupportedOperationException("not class implement");
    }
}
