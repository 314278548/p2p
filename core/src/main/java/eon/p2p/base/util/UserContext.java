package eon.p2p.base.util;

import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.vo.VerifyCode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 用户上下文
 */
@Component
public class UserContext {
    final public static String USER_IN_SESSION = "loginInfo";
    final public static String VERIFYCODE_IN_SESSION = "verifyCode_in_session";

    /**
     * 获取当前用户的请求
     *
     * @return
     */
    public static HttpServletRequest getCurrentRequest() {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return requestAttributes.getRequest();
    }

    /**
     * 把当前用户存入session
     *
     * @param logininfo
     */
    public static void setCurrent(Logininfo logininfo) {
        HttpServletRequest request = getCurrentRequest();
        request.getSession().setAttribute(USER_IN_SESSION, logininfo);
    }

    /**
     * 从session中获取当前用户
     *
     * @return
     */
    public static Logininfo getCurrent() {
        HttpSession session = getCurrentRequest().getSession();
        return (Logininfo) session.getAttribute(USER_IN_SESSION);
    }

    /**
     * 获取当前用户类型
     *
     * @return
     */
    public static int getUserType() {
        return getCurrent().getUserType();
    }


    /**
     * 获取当前用户验证码对象
     *
     * @return
     */
    public static VerifyCode getCurrentVerifyCode() {
        return (VerifyCode) getCurrentRequest().getSession().getAttribute(UserContext.VERIFYCODE_IN_SESSION);
    }

    /**
     * 设置当前用户验证码对象
     *
     * @param verifyCode
     */
    public static void setCurrentVerifyCode(VerifyCode verifyCode) {
        getCurrentRequest().getSession().setAttribute(UserContext.VERIFYCODE_IN_SESSION, verifyCode);
    }
}
