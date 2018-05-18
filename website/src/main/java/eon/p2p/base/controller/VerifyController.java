package eon.p2p.base.controller;

import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.service.IVerfiyService;
import eon.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VerifyController {

    @Autowired
    private IVerfiyService verfiyService;
    @Autowired
    private IUserInfoService userInfoService;

    @RequestMapping("/sendVerifyCode.do")
    @ResponseBody
    public AjaxResult verify(String phoneNumber) {
        try {
            verfiyService.sendVerifyCode(
                    phoneNumber);
            return new AjaxResult("成功发送!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new AjaxResult(false, e.getMessage());
        }
    }

    @RequestMapping("/bindPhone.do")
    @ResponseBody
    public AjaxResult bind(String phoneNumber, String verifyCode) {
        try {
            userInfoService.bindPhone(phoneNumber, verifyCode);
            return new AjaxResult("成功绑定!");
        } catch (RuntimeException e) {
            e.printStackTrace();
            return new AjaxResult(false, e.getMessage());
        }
    }

    @RequestMapping("/phoneIsUsed.do")
    @ResponseBody
    public AjaxResult phoneIsUsed(String phoneNumber) {
        return userInfoService.phoneIsUsed(phoneNumber) ? new AjaxResult(false, "此手机号已被绑定") : new AjaxResult("此手机号可用");
    }
}
