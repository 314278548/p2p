package eon.p2p.base.service.impl;

import eon.p2p.base.domain.Account;
import eon.p2p.base.domain.AccountFlow;
import eon.p2p.base.domain.Bid;
import eon.p2p.base.domain.RechargeOffline;
import eon.p2p.base.mapper.AccountFlowMapper;
import eon.p2p.base.service.IAccountFlowService;
import eon.p2p.base.util.BidConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountFlowService implements IAccountFlowService {
    @Autowired
    private AccountFlowMapper accountFlowMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return accountFlowMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(AccountFlow record) {
        return accountFlowMapper.insert(record);
    }

    @Override
    public AccountFlow selectByPrimaryKey(Long id) {
        return accountFlowMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<AccountFlow> selectAll() {
        return accountFlowMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(AccountFlow record) {
        return accountFlowMapper.updateByPrimaryKey(record);
    }

    private AccountFlow createBaseFlow(Account account) {
        AccountFlow flow = new AccountFlow();
        flow.setAccount(account);//关联流水账户
        flow.setActionTime(new Date());//设置流水时间
        flow.setBalance(account.getUsableAmount());//账户可用余额
        flow.setFreezed(account.getFreezedAmount());//账户冻结金额
        return flow;
    }

    @Override
    public void addRechargeFlow(RechargeOffline rechargeOffline, Account account) {
        AccountFlow flow = createBaseFlow(account);
        flow.setAccountActionType(BidConst.ACCOUNT_ACTIONTYPE_DEPOSIT_OFFLINE_LOCAL);//线下支付流水类型
        flow.setAmount(rechargeOffline.getAmount());//充值金额
        flow.setNote("线下充值成功!" + rechargeOffline.getAmount());
        accountFlowMapper.insert(flow);
    }

    @Override
    public void createBid(Bid bid, Account account) {
        AccountFlow flow = createBaseFlow(account);
        flow.setAccountActionType(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);
        flow.setAmount(bid.getAvailableAmount());//设置流水金额
        flow.setNote("投标,冻结账户金额" + bid.getAvailableAmount());
        this.accountFlowMapper.insert(flow);
    }
}
