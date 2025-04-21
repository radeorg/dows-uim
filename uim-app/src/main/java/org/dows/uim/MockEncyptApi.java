package org.dows.uim;

import lombok.extern.slf4j.Slf4j;
import org.dows.rade.encrypt.EncryptApi;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockEncyptApi implements EncryptApi {
    @Override
    public String getBCryptPassword(String password) {
        return EncryptApi.super.getBCryptPassword(password);
    }
}
