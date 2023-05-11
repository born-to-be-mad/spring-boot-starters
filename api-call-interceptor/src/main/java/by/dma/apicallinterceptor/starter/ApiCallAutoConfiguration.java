package by.dma.apicallinterceptor.starter;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import by.dma.apicallinterceptor.starter.config.AuthVerificationProperties;
import by.dma.apicallinterceptor.starter.interceptor.ApiCallInterceptor;
import by.dma.apicallinterceptor.starter.interceptor.LoggedUserInterceptor;
import by.dma.apicallinterceptor.starter.service.AnotherServiceAuthChecker;

@Configuration
@ConditionalOnProperty(value = "auth.verification.enabled", havingValue = "true")
@EnableConfigurationProperties(AuthVerificationProperties.class)
public class ApiCallAutoConfiguration implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ApiCallInterceptor(authService()));
        //registry.addInterceptor(new LoggedUserInterceptor());
    }

    @Bean
    @SessionScope
    public AnotherServiceAuthChecker authService() {
        return new AnotherServiceAuthChecker();
    }
}
