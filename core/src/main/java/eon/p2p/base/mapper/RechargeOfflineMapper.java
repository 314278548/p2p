package eon.p2p.base.mapper;

import eon.p2p.base.domain.RechargeOffline;
import eon.p2p.base.query.RechargeOfflineQueryObject;

import java.util.List;

public interface RechargeOfflineMapper {
    int deleteByPrimaryKey(Long id);

    int insert(RechargeOffline record);

    RechargeOffline selectByPrimaryKey(Long id);

    List<RechargeOffline> selectAll();

    int updateByPrimaryKey(RechargeOffline record);

    Long count(RechargeOfflineQueryObject qo);

    List<RechargeOffline> query(RechargeOfflineQueryObject qo);
}