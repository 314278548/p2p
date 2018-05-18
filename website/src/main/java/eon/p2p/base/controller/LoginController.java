package eon.p2p.base.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.service.ILogininfoService;
import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.util.AjaxResult;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 注册/登录相关
 */
@Controller
public class LoginController {
    @Autowired
    private ILogininfoService logininfoService;
    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/register.do")
    @ResponseBody
    public AjaxResult register(String username, String password) {
        try {
            logininfoService.register(username, password);
            return new AjaxResult("注册成功!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new AjaxResult(false, e.getMessage());
        }
    }

    @RequestMapping("/checkUsername.do")
    @ResponseBody
    public Boolean checkUsername(String username) {
        //当count>0时,返回false,此账号已经被注册,不能通过验证
        return !(logininfoService.selectCountByUsername(username) > 0);
    }


    @RequestMapping("/login.do")
    @ResponseBody
    public AjaxResult login(String username, String password, HttpServletRequest request, Model model) {
        Logininfo current = logininfoService.login(username, password, request.getRemoteAddr(), Logininfo.USERTYPE_NORMAL);
        if (current == null) {
            return new AjaxResult(false, "用户名或密码错误");
        }
        model.addAttribute("userInfo", userInfoService.get(current.getId()));
        return new AjaxResult("登录成功!");
    }

    @RequestMapping("/logout.do")
    public String logout() {
        Logininfo current = UserContext.getCurrent();
        if (current != null) {
            HttpSession session = UserContext.getCurrentRequest().getSession();
            session.invalidate();//清空此次会话所有信息
        }
        return "redirect:index.do";
    }

}
