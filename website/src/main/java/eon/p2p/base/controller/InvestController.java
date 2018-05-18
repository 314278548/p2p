package eon.p2p.base.controller;

import eon.p2p.base.query.BidRequestQueryObejct;
import eon.p2p.base.service.IBidRequestService;
import eon.p2p.base.util.BidConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 投资相关
 */
@Controller
public class InvestController {
    @Autowired
    private IBidRequestService bidRequestService;

    @RequestMapping("/invest.do")
    public String index() {
        return "invest";
    }

    @RequestMapping("/invest_list.do")
    public Object list(BidRequestQueryObejct qo, Model model) {
        qo.setNoState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
        model.addAttribute("pageResult", bidRequestService.query(qo));
        return "invest_list";
    }
}
