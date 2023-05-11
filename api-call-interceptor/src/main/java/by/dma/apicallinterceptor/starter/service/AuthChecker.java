package by.dma.apicallinterceptor.starter.service;

public interface AuthChecker {

    default boolean isUserActive(String userId) {
        return true;
    }

    default boolean isAccountActive(String accountId) {
        return true;
    }

    default boolean areUserAccountsAccessible(String userId, String... accountIds) {
        return true;
    }

    default boolean isUserHasFunctionalCodes(String userId, String... fcs) {
        return true;
    }

    default boolean isAccountHasFunctionalCodes(String accountId, String... fcs) {
        return true;
    }

}
