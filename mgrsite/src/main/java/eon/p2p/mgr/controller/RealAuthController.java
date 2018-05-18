package eon.p2p.mgr.controller;

import eon.p2p.base.query.RealAuthQueryObject;
import eon.p2p.base.service.IRealAuthService;
import eon.p2p.base.util.AjaxResult;
import eon.p2p.base.util.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台实名认证审核相关
 */
@Controller
public class RealAuthController {
    @Autowired
    private IRealAuthService realAuthService;

    @RequestMapping("/realAuth.do")
    @RequireLogin
    public String realAuth(@ModelAttribute("qo") RealAuthQueryObject qo, Model model) {
        model.addAttribute("pageResult", realAuthService.query(qo));
        return "/realAuth";
    }

    @RequestMapping("/realAuth_audit.do")
    @ResponseBody
    @RequireLogin
    public AjaxResult audit(Long id, int state, String remark) {
        try {
            realAuthService.audit(id, state, remark);
            return new AjaxResult("审核成功!");
        } catch (RuntimeException e) {
            return new AjaxResult(false, e.getMessage());
        }
    }
}
