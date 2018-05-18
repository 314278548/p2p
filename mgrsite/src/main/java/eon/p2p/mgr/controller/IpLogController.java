package eon.p2p.mgr.controller;

import eon.p2p.base.query.IpLogQueryObejct;
import eon.p2p.base.service.IIpLogService;
import eon.p2p.base.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletContext;

@Controller
public class IpLogController {
    @Autowired
    private IIpLogService ipLogService;

    @RequestMapping("/iplog.do")
    public String iplog(@ModelAttribute("qo") IpLogQueryObejct qo, Model model){
        //如果前台没有查看的日志用户,那么返回全部用户登录日志
        if (!StringUtil.hasLength(qo.getUsername())) {
            qo.setUsername(null);
        }
        model.addAttribute("result", ipLogService.query(qo));
        return "iplog";
    }

}
