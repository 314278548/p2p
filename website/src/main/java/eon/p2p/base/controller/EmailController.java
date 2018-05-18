package eon.p2p.base.controller;

import eon.p2p.base.service.IEmailService;
import eon.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletContext;

@Controller
public class EmailController {

    @Autowired
    private IEmailService emailService;

    @RequestMapping("/sendEmail.do")@ResponseBody
    public AjaxResult sendEmial(String email, ServletContext s){

        emailService.sendEmail(email);
        return new AjaxResult("成功");
    }
}
