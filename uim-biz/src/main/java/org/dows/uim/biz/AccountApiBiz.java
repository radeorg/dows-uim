package org.dows.uim.biz;

import org.dows.uim.api.AccountApi;
import org.dows.uim.api.request.FindAccountIdentifierRequest;
import org.dows.uim.api.response.AccountIdentifierResponse;
import org.dows.uim.api.response.AccountInstanceResponse;

public class AccountApiBiz implements AccountApi {

    @Override
    public AccountInstanceResponse getAccountInstanceByAccountName(String accountName, String appId) {
        return null;
    }

    @Override
    public AccountIdentifierResponse getAccountIdentifier(FindAccountIdentifierRequest findAccountIdentifierRequest) {
        return null;
    }

    @Override
    public AccountInstanceResponse getAccountInstanceById(Long accountIdentifier) {
        return null;
    }
}
