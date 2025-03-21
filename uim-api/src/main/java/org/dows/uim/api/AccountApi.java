package org.dows.uim.api;

import org.dows.uim.api.request.FindAccountIdentifierRequest;
import org.dows.uim.api.response.AccountIdentifierResponse;
import org.dows.uim.api.response.AccountInstanceResponse;

public interface AccountApi {
    AccountInstanceResponse getAccountInstanceByAccountName(String accountName, String appId);

    AccountIdentifierResponse getAccountIdentifier(FindAccountIdentifierRequest findAccountIdentifierRequest);

    AccountInstanceResponse getAccountInstanceById(Long accountIdentifier);
}
