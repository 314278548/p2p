package eon.p2p.base.mapper;

import eon.p2p.base.domain.RealAuth;
import eon.p2p.base.query.RealAuthQueryObject;

import java.util.List;

public interface RealAuthMapper {
    List<RealAuth> selectAll();

    RealAuth getOne(Long id);

    void insert(RealAuth realAuth);

    Long count(RealAuthQueryObject qo);

    List<RealAuth> query(RealAuthQueryObject qo);

    void update(RealAuth realAuth);

    RealAuth test(Long id);
}
