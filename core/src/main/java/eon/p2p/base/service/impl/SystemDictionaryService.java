package eon.p2p.base.service.impl;

import eon.p2p.base.domain.SystemDictionary;
import eon.p2p.base.domain.SystemDictionaryItem;
import eon.p2p.base.mapper.SystemDictionaryItemMapper;
import eon.p2p.base.mapper.SystemDictionaryMapper;
import eon.p2p.base.query.SystemDictionaryQueryObject;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.ISystemDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SystemDictionaryService implements ISystemDictionaryService {
    @Autowired
    private SystemDictionaryMapper systemDictionaryMapper;
    @Autowired
    private SystemDictionaryItemMapper systemDictionaryItemMapper;

    @Override
    public PageResult query(SystemDictionaryQueryObject qo) {
        Long count = systemDictionaryMapper.count(qo);
        if (count == 0) {
            return PageResult.EMPTY;
        }
        return new PageResult(systemDictionaryMapper.query(qo), count, qo.getCurrentPage(), qo.getPageSize());
    }


    @Override
    public void saveOrUpdate(SystemDictionary systemDictionary) {
        if (systemDictionary.getId() != null) {
            this.systemDictionaryMapper.updateByPrimaryKey(systemDictionary);
        } else {
            this.systemDictionaryMapper.insert(systemDictionary);
        }
    }
    @Override
    public void saveOrUpdate(SystemDictionaryItem item) {
        if (item.getId() != null) {
            this.systemDictionaryItemMapper.updateByPrimaryKey(item);
        } else {
            this.systemDictionaryItemMapper.insert(item);
        }
    }

    @Override
    public PageResult queryItem(SystemDictionaryQueryObject qo) {
        Long count = systemDictionaryItemMapper.count(qo);
        if (count == 0) {
            return PageResult.EMPTY;
        }
        return new PageResult(systemDictionaryItemMapper.queryItem(qo), count, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public List<SystemDictionary> selectAll() {
        return systemDictionaryMapper.selectAll();
    }

    @Override
    public List<SystemDictionaryItem> selectItemByParent(String sn) {
        return systemDictionaryMapper.selectItemByParent(sn);
    }

    @Override
    public void delete(Long id){
        systemDictionaryItemMapper.deleteByPrimaryKey(id);
    }


}
