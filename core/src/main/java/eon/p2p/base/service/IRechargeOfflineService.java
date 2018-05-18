package eon.p2p.base.service;


import eon.p2p.base.domain.RechargeOffline;
import eon.p2p.base.query.RechargeOfflineQueryObject;
import eon.p2p.base.query.page.PageResult;

import java.util.List;

public interface IRechargeOfflineService {
    int deleteByPrimaryKey(Long id);

    int insert(RechargeOffline record);

    RechargeOffline selectByPrimaryKey(Long id);

    List<RechargeOffline> selectAll();

    int updateByPrimaryKey(RechargeOffline record);

    void apply(RechargeOffline rechargeOffline);

    PageResult<RechargeOffline> query(RechargeOfflineQueryObject qo);

    void audit(int state, Long id, String remark);
}
