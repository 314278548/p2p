package eon.p2p.mgr.controller;

import eon.p2p.base.query.BankInfoQueryObject;
import eon.p2p.base.query.RechargeOfflineQueryObject;
import eon.p2p.base.service.IBankInfoService;
import eon.p2p.base.service.IRechargeOfflineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 后台线下充值申请
 */
@Controller
public class RechargeOfflineController {
    @Autowired
    private IRechargeOfflineService rechargeOfflineService;
    @Autowired
    private IBankInfoService bankInfoService;

    @RequestMapping("/rechargeOffline.do")
    public String list(@ModelAttribute("qo") RechargeOfflineQueryObject qo, Model model) {
        BankInfoQueryObject bqo = new BankInfoQueryObject();bqo.setPageSize(-1l);
        model.addAttribute("banks", this.bankInfoService.query(bqo).getData());
        model.addAttribute("pageResult",rechargeOfflineService.query(qo));
        return "recharge";
    }

    @RequestMapping("/rechargeOffline_audit.do")
    public String audit(int state,Long id,String remark){
        rechargeOfflineService.audit(state,id,remark);
        return "redirect:rechargeOffline.do";
    }
}
