package by.dma.apicallinterceptor.starter.config;

import java.util.Collections;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@ConfigurationProperties(prefix = "auth.verification")
@Data
public class AuthVerificationProperties {
    /**
     * List of checks that will be used.
     * All other checks will be ignored.
     */
    private List<AuthCheck> enabledChecks = Collections.emptyList();

    public enum AuthCheck {
        ACCOUNT_ACTIVE,
        USER_ACTIVE,

        ACCOUNTS,

        USER_PERMISSIONS,

        ACCOUNT_PERMISSIONS

    }


}
