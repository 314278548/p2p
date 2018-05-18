package eon.p2p.base.controller;

import eon.p2p.base.domain.*;
import eon.p2p.base.query.UserFileQueryObject;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.*;
import eon.p2p.base.util.RequireLogin;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 借款相关
 */
@Controller
public class BorrowController {
    @Autowired
    private IBidRequestService bidRequestService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IRealAuthService realAuthService;
    @Autowired
    private IUserFileService userFileService;

    @RequestMapping("/borrow.do")
    @RequireLogin
    public String borrow(Model model) {
        model.addAttribute("userinfo", userInfoService.getCurrent());
        model.addAttribute("account", accountService.getCurrent());
        return "borrow";
    }

    @RequestMapping("/borrow_info.do")
    public String borrowInfo(Long id, Model model) {
        //bidRequest
        BidRequest bidRequest = bidRequestService.get(id);
        if (bidRequest != null) {
            model.addAttribute("bidRequest", bidRequest);
            UserInfo userInfo = userInfoService.get(bidRequest.getCreateUser().getId());
            model.addAttribute("userInfo", userInfo);
            model.addAttribute("realAuth", realAuthService.get(userInfo.getRealAuthId()));
            UserFileQueryObject qo = new UserFileQueryObject();
            qo.setPageSize(-1l);
            qo.setState(UserFile.STATE_PASS);
            qo.setApplierId(userInfo.getId());
            PageResult<UserFile> query = userFileService.query(qo);
            model.addAttribute("userFiles", query.getData());
            if (UserContext.getCurrent() != null) {
                if (UserContext.getCurrent().getId() == bidRequest.getCreateUser().getId()) {
                    //当前用户为借款对象
                    model.addAttribute("self", true);
                } else {
                    model.addAttribute("self", false);
                    model.addAttribute("account", accountService.getCurrent());
                }
            }else{
                //没有登录
                model.addAttribute("self",false);
            }
        }
        //account
        //self
        return "borrow_info";
    }

    /**
     * 进入借款界面
     *
     * @param model
     * @return
     */
    @RequestMapping("/apply.do")
    public String borrowApply(Model model) {
        Logininfo current = UserContext.getCurrent();
        if (bidRequestService.canApply(current.getId())) {
            model.addAttribute("account", accountService.get(current.getId()));
            return "borrow_apply";
        } else {
            return "borrow_apply_result";
        }
    }
}
