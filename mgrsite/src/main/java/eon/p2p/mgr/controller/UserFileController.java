package eon.p2p.mgr.controller;

import eon.p2p.base.domain.UserFile;
import eon.p2p.base.query.UserFileQueryObject;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.IUserFileService;
import eon.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserFileController {

    @Autowired
    private IUserFileService userFileService;

    @RequestMapping("userFileAuth")
    public String userFileList(@ModelAttribute("qo") UserFileQueryObject qo,
                               Model model) {
        PageResult<UserFile> result = this.userFileService.query(qo);
        model.addAttribute("pageResult", result);
        return "userFile";
    }

    @RequestMapping("userFile_audit")
    @ResponseBody
    public AjaxResult userFileAudit(Long id, String remark, int score, int state) {
        try {
            this.userFileService.audit(id, remark, score, state);
            return new AjaxResult("成功");
        } catch (Exception e) {
            return new AjaxResult(false, e.getMessage());
        }
    }

}
