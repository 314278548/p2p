package eon.p2p.mgr.controller;

import eon.p2p.base.domain.SystemDictionary;
import eon.p2p.base.domain.SystemDictionaryItem;
import eon.p2p.base.query.SystemDictionaryQueryObject;
import eon.p2p.base.service.ISystemDictionaryService;
import eon.p2p.base.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 后台字典与字典明细相关服务
 */
@Controller
public class SystemDictionaryController {
    @Autowired
    private ISystemDictionaryService systemDictionaryService;

    /**
     * 数据字典的高级查询
     *
     * @param qo
     * @param model
     * @return
     */
    @RequestMapping("/systemDictionary_list.do")
    public String query(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
        model.addAttribute("result", systemDictionaryService.query(qo));
        return "systemDictionary_list";
    }

    /**
     * 数据字典明细的高级查询
     *
     * @param qo
     * @param model
     * @return
     */
    @RequestMapping("/systemDictionaryItem_list.do")
    public String queryItem(@ModelAttribute("qo") SystemDictionaryQueryObject qo, Model model) {
        List<SystemDictionary> systemDictionaries = systemDictionaryService.selectAll();
        if (qo.getParentId() == null) {
            qo.setParentId(systemDictionaries.get(0).getId());
        }
        model.addAttribute("pageResult", systemDictionaryService.queryItem(qo));
        model.addAttribute("systemDictionaryGroups", systemDictionaries);
        model.addAttribute("firstId", systemDictionaries.get(0).getId());
        return "systemDictionaryItem_list";
    }

    /**
     * 数据字典的更新保存操作
     *
     * @param systemDictionary
     * @return
     */
    @RequestMapping("/systemDictionary_saveOrUpdate.do")
    @ResponseBody
    public AjaxResult saveOrUpdate(SystemDictionary systemDictionary) {
        try {
            systemDictionaryService.saveOrUpdate(systemDictionary);
            return new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "失败,请联系管理员");
        }
    }

    /**
     * 数据字典明细的更新保存操作
     *
     * @param item
     * @return
     */
    @RequestMapping("/systemDictionaryItem_saveOrUpdate.do")
    @ResponseBody
    public AjaxResult saveOrUpdate(SystemDictionaryItem item) {
        try {
            systemDictionaryService.saveOrUpdate(item);
            return new AjaxResult("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "失败,请联系管理员");
        }
    }


    @RequestMapping("/systemDictionaryItem_delete.do")
    @ResponseBody
    public AjaxResult delete(Long id) {
        try {
            systemDictionaryService.delete(id);
            return new AjaxResult("成功");
        } catch (Exception e) {
            return new AjaxResult(false, "失败");
        }
    }
}
