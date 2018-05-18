package eon.p2p.base.service;

import eon.p2p.base.domain.SystemDictionary;
import eon.p2p.base.domain.SystemDictionaryItem;
import eon.p2p.base.query.SystemDictionaryQueryObject;
import eon.p2p.base.query.page.PageResult;

import java.util.List;

/**
 * 后台字典目录相关类
 */
public interface ISystemDictionaryService {
    /**
     * 高级查询
     *
     * @param qo
     */
    PageResult query(SystemDictionaryQueryObject qo);

    void saveOrUpdate(SystemDictionary systemDictionary);

    void saveOrUpdate(SystemDictionaryItem item);

    PageResult queryItem(SystemDictionaryQueryObject qo);

    List<SystemDictionary> selectAll();

    List<SystemDictionaryItem> selectItemByParent(String sn);

    void delete(Long id);
}
