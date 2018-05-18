package eon.p2p.base.interceptor;

import eon.p2p.base.util.RequireLogin;
import eon.p2p.base.util.UserContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户登录拦截
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod method = (HandlerMethod) handler;
            RequireLogin methodAnnotation = method.getMethodAnnotation(RequireLogin.class);
            //当前需要登录的操作
            if (methodAnnotation != null && UserContext.getCurrent() == null) {
                response.sendRedirect("/login.html");
                return false;
            }
            //当前不需要登录
            return true;
        }
        return true;
    }
}
