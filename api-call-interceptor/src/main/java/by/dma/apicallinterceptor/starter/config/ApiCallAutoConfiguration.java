package by.dma.apicallinterceptor.starter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import by.dma.apicallinterceptor.starter.interceptor.ApiCallInterceptor;
import by.dma.apicallinterceptor.starter.interceptor.LoggedUserInterceptor;

@Configuration
@ConditionalOnProperty(value = "auth.verification.enabled", havingValue = "true")
@EnableConfigurationProperties(AuthVerificationProperties.class)
public class ApiCallAutoConfiguration implements WebMvcConfigurer {

    private final AuthVerificationProperties properties;

    public ApiCallAutoConfiguration(AuthVerificationProperties properties) {
        this.properties = properties;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiCallInterceptor(properties));
        registry.addInterceptor(new LoggedUserInterceptor());
    }

    @Bean
    public ApiCallInterceptor apiCallInterceptor(AuthVerificationProperties properties) {
        return new ApiCallInterceptor(properties);
    }

/*     @Bean
    @SessionScope
    public AuthService authService() {
        return new AuthService();
    } */
}
