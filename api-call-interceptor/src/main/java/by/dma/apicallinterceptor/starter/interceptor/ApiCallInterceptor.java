package by.dma.apicallinterceptor.starter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import by.dma.apicallinterceptor.starter.service.AnotherServiceAuthChecker;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@AllArgsConstructor
public class ApiCallInterceptor implements HandlerInterceptor {

    private final AnotherServiceAuthChecker authChecker;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        log.info(decorateMessage("PreHandle - main authentication logic", request));
        log.debug("[preHandle][" + request + "]" + "[" + request.getMethod()
                  + "]" + request.getRequestURI() + sanitizeParameters(request));
        boolean isExcluded = isExcluded(handler);
        if (!isExcluded) {
            authChecker.init();
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {

        log.info(decorateMessage("PostHandle", request));
    }

    private boolean isExcluded(Object handler) {
        if (handler instanceof HandlerMethod handlerMethod) {
            var isExcluded = handlerMethod.getBeanType().isAnnotationPresent(ExcludeFromInterception.class) ||
                             handlerMethod.getMethodAnnotation(ExcludeFromInterception.class) != null;
            if (isExcluded) {
                log.debug("Method {} is excluded from interception", handlerMethod.getMethod().getName());
            }
            return isExcluded;
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {

        log.info(decorateMessage("AfterCompletion", request));
        if (ex != null) {
            ex.printStackTrace();
        }
    }

    private String decorateMessage(String message, HttpServletRequest request) {
        var tag = "#".repeat(5);
        return String.format("%s [%s][request=%s] %s", tag, message, request, tag);
    }

    private boolean isEnabled(String authCheck) {
        // return properties.getEnabledChecks().contains(authCheck);
        return true;
    }

    private String sanitizeParameters(HttpServletRequest request) {
        StringBuffer posted = new StringBuffer();
        Enumeration<?> e = request.getParameterNames();
        if (e == null) {
            return "";
        }
        posted.append("?");
        while (e.hasMoreElements()) {
            if (posted.length() > 1) {
                posted.append("&");
            }
            String curr = (String) e.nextElement();
            posted.append(curr).append("=");
            if (curr.contains("password") || curr.contains("pass") || curr.contains("pwd")) {
                posted.append("*****");
            } else {
                posted.append(request.getParameter(curr));
            }
        }
        String ip = request.getHeader("X-FORWARDED-FOR");
        String ipAddr = Optional.ofNullable(ip).orElseGet(() -> getRemoteAddr(request));
        if (StringUtils.hasText(ipAddr)) {
            posted.append("&_psip=").append(ipAddr);
        }
        return posted.toString();
    }

    private String getRemoteAddr(HttpServletRequest request) {
        String ipFromHeader = request.getHeader("X-FORWARDED-FOR");
        if (ipFromHeader != null && ipFromHeader.length() > 0) {
            log.debug("ip from proxy - X-FORWARDED-FOR : " + ipFromHeader);
            return ipFromHeader;
        }
        return request.getRemoteAddr();
    }
}
