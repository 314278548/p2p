package eon.p2p.base.mapper;

import eon.p2p.base.domain.SystemDictionary;
import eon.p2p.base.domain.SystemDictionaryItem;
import eon.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionary record);

    SystemDictionary selectByPrimaryKey(Long id);

    List<SystemDictionary> selectAll();

    int updateByPrimaryKey(SystemDictionary record);

    Long count(SystemDictionaryQueryObject qo);

    List query(SystemDictionaryQueryObject qo);

    List<SystemDictionaryItem> selectItemByParent(String sn);
}