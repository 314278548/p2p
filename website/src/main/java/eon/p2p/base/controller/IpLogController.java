package eon.p2p.base.controller;

import eon.p2p.base.domain.IpLog;
import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.query.IpLogQueryObejct;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.IIpLogService;
import eon.p2p.base.util.RequireLogin;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IpLogController {
    @Autowired
    private IIpLogService ipLogService;

    @RequestMapping("/iplog.do")@RequireLogin
    public String ipLog(@ModelAttribute("qo") IpLogQueryObejct qo, Model model) {
        //前台用户只能看自己的登录记录
        qo.setUsername(UserContext.getCurrent().getUsername());
        //前台用户只能看前台用户的登录记录
        qo.setUserType(Logininfo.USERTYPE_NORMAL);
        model.addAttribute("pageResult", ipLogService.query(qo));
        return "iplog";
    }

    @RequestMapping("/iplog_test.do")@ResponseBody
    public PageResult<IpLog> test(@ModelAttribute("qo") IpLogQueryObejct qo, Model model) {
        //前台用户只能看自己的登录记录
        qo.setUsername(UserContext.getCurrent().getUsername());
        //前台用户只能看前台用户的登录记录
        qo.setUserType(Logininfo.USERTYPE_NORMAL);
        return ipLogService.query(qo);
    }


}
