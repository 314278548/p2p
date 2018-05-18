package eon.p2p.base.service;

import eon.p2p.base.domain.Account;
import eon.p2p.base.domain.AccountFlow;
import eon.p2p.base.domain.Bid;
import eon.p2p.base.domain.RechargeOffline;

import java.util.List;

public interface IAccountFlowService {
    int deleteByPrimaryKey(Long id);

    int insert(AccountFlow record);

    AccountFlow selectByPrimaryKey(Long id);

    List<AccountFlow> selectAll();

    int updateByPrimaryKey(AccountFlow record);

    void addRechargeFlow(RechargeOffline rechargeOffline, Account account);

    void createBid(Bid bid, Account account);
}
