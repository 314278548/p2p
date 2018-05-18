package eon.p2p.mgr.controller;

import eon.p2p.base.domain.BankInfo;
import eon.p2p.base.query.BankInfoQueryObject;
import eon.p2p.base.service.IBankInfoService;
import eon.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 后台公司银行账户管理相关
 */
@Controller
public class BankInfoController {

    @Autowired
    private IBankInfoService bankInfoService;

    @RequestMapping("/bankInfo.do")
    public String bankInfo(BankInfoQueryObject qo, Model model) {
        model.addAttribute("pageResult", bankInfoService.query(qo));
        return "bankInfo";
    }

    @RequestMapping("bankInfo_updateOrSave.do")
    public String upS(BankInfo bankInfo) {
            bankInfoService.saveOrUpdate(bankInfo);
            return "redirect:bankInfo.do";
    }
}
