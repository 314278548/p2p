package eon.p2p.base.controller;

import eon.p2p.base.domain.RealAuth;
import eon.p2p.base.domain.UserInfo;
import eon.p2p.base.service.IRealAuthService;
import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.util.AjaxResult;
import eon.p2p.base.util.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * 前台用户实名认证相关服务
 */
@Controller
public class RealAuthController {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IRealAuthService realAuthService;

    @RequestMapping("/realAuth.do")
    @RequireLogin
    public String index(Model model) {
        UserInfo current = userInfoService.getCurrent();
        if (current.isBindAuth()) {
            model.addAttribute("realAuth", realAuthService.get(current.getRealAuthId()));
            model.addAttribute("auditing", false);//审核完成了
            return "realAuth_result";
        } else {
            //没有实名认证
            if (current.getRealAuthId() == null) {
                return "realAuth";
            } else {
                model.addAttribute("auditing", true);//正在审核中
                return "realAuth_result";
            }
        }
    }

    @RequestMapping("/realAuthUpload.do")
    @ResponseBody
    public String upload(MultipartFile file, Model model, HttpServletResponse response) {
        String upload = realAuthService.upload(file);
        return "{'path':'" + upload + "'}";
    }

    @RequestMapping("/realAuth_save.do")
    @ResponseBody
    @RequireLogin
    public AjaxResult save(RealAuth realAuth) {
        try {
            realAuthService.apply(realAuth);
            return new AjaxResult("成功");
        } catch (RuntimeException e) {
            return new AjaxResult(false, e.getMessage());
        }
    }


}
