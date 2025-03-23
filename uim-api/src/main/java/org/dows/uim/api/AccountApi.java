package org.dows.uim.api;

import org.dows.uim.api.request.AccountInstanceRequest;
import org.dows.uim.api.request.FindAccountIdentifierRequest;
import org.dows.uim.api.response.AccountIdentifierResponse;
import org.dows.uim.api.response.AccountInstanceResponse;
import org.dows.uim.api.response.AccountOrgIdsResponse;
import org.dows.uim.api.response.AccountRoleRelationResponse;

import java.util.List;

public interface AccountApi {
    Long setAccountInstance(AccountInstanceRequest accountInstance);

    AccountInstanceResponse getAccountInstanceByAccountName(String accountName, String appId);

    AccountIdentifierResponse getAccountIdentifier(FindAccountIdentifierRequest findAccountIdentifierRequest);

    AccountInstanceResponse getAccountInstanceById(Long accountIdentifier);

    AccountOrgIdsResponse getOrgIdsByAccountId(Long accountInstanceId, boolean check, String appId);

    List<AccountRoleRelationResponse>  getRoleByAccountInstanceId(List<Long> principals,String appId);
}
