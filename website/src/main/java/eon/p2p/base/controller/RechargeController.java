package eon.p2p.base.controller;

import com.sun.tools.internal.ws.processor.model.Model;
import eon.p2p.base.domain.RechargeOffline;
import eon.p2p.base.query.BankInfoQueryObject;
import eon.p2p.base.service.IBankInfoService;
import eon.p2p.base.service.IRechargeOfflineService;
import eon.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 账户充值相关
 */
@Controller
public class RechargeController {
    @Autowired
    private IBankInfoService bankInfoService;

    @Autowired
    private IRechargeOfflineService rechargeOfflineService;

    /**
     * 线下充值页面
     *
     * @return
     */
    @RequestMapping("recharge")
    public String recharge(org.springframework.ui.Model model) {
        BankInfoQueryObject qo = new BankInfoQueryObject();qo.setPageSize(-1l);
        model.addAttribute("banks", this.bankInfoService.query(qo).getData());
        return "recharge";
    }

    @RequestMapping("recharge_save")
    @ResponseBody
    public AjaxResult saveRecharge(RechargeOffline recharge) {
        AjaxResult json = new AjaxResult("");
        try {
            this.rechargeOfflineService.apply(recharge);
        } catch (Exception e) {
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }

}
