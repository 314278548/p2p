package eon.p2p.mgr.controller;

import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.service.ILogininfoService;
import eon.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private ILogininfoService logininfoService;

    @RequestMapping("/login.do")
    @ResponseBody
    public AjaxResult login(String username, String password, HttpServletRequest request) {
        Logininfo login = logininfoService.login(username, password, request.getRemoteAddr(), Logininfo.USERTYPE_SYSTEM);
        if (login == null) {
            return new AjaxResult(false, "用户名或密码错误");
        }
        return new AjaxResult("登录成功!");
    }

    @RequestMapping("/main.do")
    public String main() {
        return "main";
    }
}
