package eon.p2p.base.controller;

import eon.p2p.base.service.IBidService;
import eon.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

/**
 * 前台投标相关
 */
@Controller
public class BidController {
    @Autowired
    private IBidService bidService;

    @RequestMapping("/borrow_bid.do")
    @ResponseBody
    public AjaxResult bid(Long bidRequestId, BigDecimal amount) {
        try {
            bidService.bid(bidRequestId, amount);
            return new AjaxResult("");
        } catch (Exception e) {
            return new AjaxResult(false, "");
        }
    }
}
