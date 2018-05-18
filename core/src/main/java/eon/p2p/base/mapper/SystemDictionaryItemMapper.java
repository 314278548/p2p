package eon.p2p.base.mapper;

import eon.p2p.base.domain.SystemDictionaryItem;
import eon.p2p.base.query.SystemDictionaryQueryObject;

import java.util.List;

public interface SystemDictionaryItemMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SystemDictionaryItem record);

    SystemDictionaryItem selectByPrimaryKey(Long id);

    List<SystemDictionaryItem> selectAll();

    int updateByPrimaryKey(SystemDictionaryItem record);

    Long count(SystemDictionaryQueryObject qo);

    List<SystemDictionaryItem> queryItem(SystemDictionaryQueryObject qo);
}