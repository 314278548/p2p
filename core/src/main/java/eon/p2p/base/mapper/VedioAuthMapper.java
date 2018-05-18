package eon.p2p.base.mapper;

import eon.p2p.base.domain.VedioAuth;
import eon.p2p.base.query.VedioQueryObject;
import java.util.List;

public interface VedioAuthMapper {

    int insert(VedioAuth record);

    VedioAuth selectByPrimaryKey(Long id);

    Long count(VedioQueryObject qo);

    List query(VedioQueryObject qo);
}