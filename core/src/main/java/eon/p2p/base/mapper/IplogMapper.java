package eon.p2p.base.mapper;

import eon.p2p.base.domain.IpLog;
import eon.p2p.base.query.IpLogQueryObejct;

import java.util.List;

public interface IplogMapper {

    List<IpLog> selectAll(IpLogQueryObejct qo);

    void insert(IpLog ipLog);

    Long count(IpLogQueryObejct qo);

}