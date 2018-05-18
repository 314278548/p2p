package eon.p2p.base.controller;

import eon.p2p.base.domain.UserInfo;
import eon.p2p.base.service.ISystemDictionaryService;
import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.util.RequireLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserInfoController {
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    @RequestMapping("/basicInfo.do")@RequireLogin
    public String index(Model model) {
        model.addAttribute("userInfo", userInfoService.getCurrent());
        model.addAttribute("educationBackground", systemDictionaryService.selectItemByParent("educationBackground"));
        model.addAttribute("incomeGrade", systemDictionaryService.selectItemByParent("incomeGrade"));
        model.addAttribute("marriage", systemDictionaryService.selectItemByParent("marriage"));
        model.addAttribute("kidCount", systemDictionaryService.selectItemByParent("kidCount"));
        System.err.println(userInfoService.getCurrent()
                .getKidCount());
        model.addAttribute("houseCondition", systemDictionaryService.selectItemByParent("houseCondition"));
        return "userInfo";
    }

    @RequestMapping("/basicInfo_save.do")@RequireLogin
    public String update(UserInfo userInfo) {
        userInfoService.updateUserInfo(userInfo);
        return "userInfo";
    }
}
