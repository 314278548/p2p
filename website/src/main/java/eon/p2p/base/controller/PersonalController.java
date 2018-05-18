package eon.p2p.base.controller;

import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.domain.UserInfo;
import eon.p2p.base.service.IAccountService;
import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.util.RequireLogin;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PersonalController {

    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/personal.do")
    @RequireLogin
    public String personal(Model model) {
        Logininfo userInfo = UserContext.getCurrent();
        model.addAttribute("userInfo",
                userInfoService.get(userInfo.getId()));
        model.addAttribute("account", accountService.get(userInfo.getId()));
        return "personal";
    }
}
