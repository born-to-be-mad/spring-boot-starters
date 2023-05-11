package by.dma.apicallinterceptor.starter.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.SmartView;
import org.springframework.web.servlet.View;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggedUserInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {

        if (isUserLogged()) {
            populateUserDataToSession(request.getSession());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView mv) throws Exception {

        if (mv != null && !isRedirectView(mv)) {
            if (isUserLogged()) {
                addToModelUserDetails(mv);
            }
        }
    }

    private void populateUserDataToSession(HttpSession session) {
        log.info("================= populateUserDataToSession ============================");
        var loggedUser = getLoggedUser();
        session.setAttribute("username", loggedUser);
        log.info("user(" + loggedUser + ") session : " + session);
        log.info("================= populateUserDataToSession ============================");

    }

    private String getLoggedUser() {
        /* return SecurityContextHolder.getContext()
                .getAuthentication()
                .getName(); */
        return "dma";
    }

    /**
     * Used when model is available
     */
    private void addToModelUserDetails(ModelAndView model) {
        log.info("================= addToModelUserDetails ============================");
        var loggedUser = getLoggedUser();
        model.addObject("loggedUsername", loggedUser);
        log.trace("session : " + model.getModel());
        log.info("================= addToModelUserDetails ============================");

    }

    public static boolean isRedirectView(ModelAndView mv) {
        String viewName = mv.getViewName();
        if (viewName != null && viewName.startsWith("redirect:/")) {
            return true;
        }
        View view = mv.getView();
        return (view instanceof SmartView && ((SmartView) view).isRedirectView());
    }

    public static boolean isUserLogged() {
        try {
/*             return !SecurityContextHolder.getContext()
                    .getAuthentication()
                    .getName()
                    .equals("anonymousUser"); */
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
