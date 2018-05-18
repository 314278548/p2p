package eon.p2p.base.controller;

import eon.p2p.base.domain.BidRequest;
import eon.p2p.base.service.IBidRequestService;
import eon.p2p.base.service.impl.BidRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台借款相关
 */
@Controller
public class BidRequestController {
    @Autowired
    private IBidRequestService bidRequestService;

    @RequestMapping("/borrow_apply.do")
    public String applpy(BidRequest bidRequest) {
        this.bidRequestService.apply(bidRequest);
        return "redirect:apply.do";
    }
}
