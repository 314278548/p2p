package eon.p2p.base.controller;

import eon.p2p.base.domain.Bid;
import eon.p2p.base.domain.BidRequest;
import eon.p2p.base.query.BidRequestQueryObejct;
import eon.p2p.base.query.QueryObject;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.IBidRequestService;
import eon.p2p.base.util.BidConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台首页相关
 */
@Controller
public class IndexController {
    @Autowired
    private IBidRequestService bidRequestService;

    @RequestMapping("/index.do")
    public String index(Model model) {
        BidRequestQueryObejct qo = new BidRequestQueryObejct();
        qo.setStates(new int[]{BidConst.BIDREQUEST_STATE_BIDDING, BidConst.BIDREQUEST_STATE_PAYING_BACK, BidConst.BIDREQUEST_STATE_COMPLETE_PAY_BACK});
        qo.setOrderBy("bidRequestState");
        qo.setOrderType("desc");
        PageResult<BidRequest> query = bidRequestService.query(qo);
        model.addAttribute("bidRequests", query.getData());
        return "main";
    }
}
