package by.dma.apicallinterceptor.starter.service;

import lombok.extern.slf4j.Slf4j;

/* @Service
@SessionScope */
@Slf4j
public class AnotherServiceAuthChecker implements AuthChecker {

    // configure WebClient
    /// functional codes
    // accounts
    private boolean isInitialized;

    public void init() {
        if (!isInitialized) {
            log.info("Initialize permissions from another service with TTL");
            isInitialized = true;
        }
    }

    @Override
    public boolean isAccountActive(String accountId) {
        log.info("AnotherServiceAuthChecker.isAccountActive: accountId={}", accountId);
        return AuthChecker.super.isAccountActive(accountId);
    }
}
