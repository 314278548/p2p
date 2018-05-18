package eon.p2p.mgr.controller;

import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.query.VedioQueryObject;
import eon.p2p.base.service.IRealAuthService;
import eon.p2p.base.service.IVedioAuthService;
import eon.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台视频审核服务
 */
@Controller
public class VedioRealController {
    @Autowired
    private IVedioAuthService vedioAuthService;
    @Autowired
    private IRealAuthService realAuthService;

    @RequestMapping("/vedioAuth.do")
    public String index(@ModelAttribute("qo") VedioQueryObject qo, Model model) {
        model.addAttribute("pageResult", vedioAuthService.query(qo));
        return "/vedioAuth";
    }

    @RequestMapping("/vedioAuth_audit")
    @ResponseBody
    public AjaxResult audit(String remark, int state, Long loginInfoValue) {
        try {
            vedioAuthService.audit(remark, state, loginInfoValue);
            return new AjaxResult("成功");
        } catch (RuntimeException e) {
            return new AjaxResult(false, e.getMessage());
        }

    }


    @RequestMapping("/vedioAuth_autocomplate.do")
    @ResponseBody
    public List<Logininfo> list(String keyword) {
        return realAuthService.listByKeyword(keyword);
    }
}
