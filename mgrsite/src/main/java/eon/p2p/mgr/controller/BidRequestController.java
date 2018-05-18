package eon.p2p.mgr.controller;

import eon.p2p.base.domain.*;
import eon.p2p.base.query.BidRequestQueryObejct;
import eon.p2p.base.query.RealAuthQueryObject;
import eon.p2p.base.query.UserFileQueryObject;
import eon.p2p.base.service.*;
import eon.p2p.base.util.AjaxResult;
import eon.p2p.base.util.BidConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * 后台发标审核相关
 */
@Controller
public class BidRequestController {
    @Autowired
    private IBidRequestService bidRequestService;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IBidHistoryService bidHistoryService;
    @Autowired
    private IRealAuthService realAuthService;
    @Autowired
    private IUserFileService userFileService;

    @RequestMapping("/bidrequest_publishaudit_list.do")
    public String audit(@ModelAttribute("qo") BidRequestQueryObejct qo, Model model) {
        qo.setState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
        model.addAttribute("pageResult", bidRequestService.query(qo));
        return "publish_audit";
    }

    @RequestMapping("/bidrequest_publishaudit.do")
    @ResponseBody
    public AjaxResult pulishAudit(Long id, int state, String remark) {
        try {
            bidRequestService.audit(id, state, remark);
            return new AjaxResult("成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "失败");
        }
    }

    @RequestMapping
    public String info(Long id, Model model) {
        //bidRequest
        BidRequest bidRequest = bidRequestService.get(id);
        model.addAttribute("bidRequest", bidRequest);
        //userinfo
        UserInfo userInfo = userInfoService.get(bidRequest.getCreateUser().getId());
        model.addAttribute("userInfo", userInfo);
        //audits历史审核信息
        List<BidRequestAuditHistory> audits = bidHistoryService.selectByBid(bidRequest.getId());
        model.addAttribute("audits", audits);
        //借款人实名信息
        model.addAttribute("realAuth", realAuthService.get(userInfo.getRealAuthId()));
        //该借款人的分控资
        UserFileQueryObject qo = new UserFileQueryObject();
        qo.setState(UserFile.STATE_PASS);
        qo.setPageSize(-1l);
        qo.setApplierId(userInfo.getId());
        model.addAttribute("userFiles", userFileService.query(qo).getData());
        return "borrow_info";
    }
}
