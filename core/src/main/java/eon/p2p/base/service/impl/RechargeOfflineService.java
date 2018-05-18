package eon.p2p.base.service.impl;

import eon.p2p.base.domain.Account;
import eon.p2p.base.domain.BaseAuditDomain;
import eon.p2p.base.domain.RechargeOffline;
import eon.p2p.base.mapper.AccountMapper;
import eon.p2p.base.mapper.RechargeOfflineMapper;
import eon.p2p.base.query.RechargeOfflineQueryObject;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.IAccountFlowService;
import eon.p2p.base.service.IAccountService;
import eon.p2p.base.service.IRechargeOfflineService;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RechargeOfflineService implements IRechargeOfflineService {
    @Autowired
    private RechargeOfflineMapper rechargeOfflineMapper;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountFlowService accountFlowService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return rechargeOfflineMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(RechargeOffline record) {
        return rechargeOfflineMapper.insert(record);
    }

    @Override
    public RechargeOffline selectByPrimaryKey(Long id) {
        return rechargeOfflineMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<RechargeOffline> selectAll() {
        return rechargeOfflineMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(RechargeOffline record) {
        return rechargeOfflineMapper.updateByPrimaryKey(record);
    }

    @Override
    public void apply(RechargeOffline rechargeOffline) {
        rechargeOffline.setApplyTime(new Date());
        rechargeOffline.setApplier(UserContext.getCurrent());
        rechargeOffline.setState(BaseAuditDomain.STATE_APPLY);
        insert(rechargeOffline);
    }

    @Override
    public PageResult<RechargeOffline> query(RechargeOfflineQueryObject qo) {
        Long count = rechargeOfflineMapper.count(qo);
        if (count == 0) {
            return PageResult.EMPTY;
        } else {
            return new PageResult<>(rechargeOfflineMapper.query(qo), count, qo.getCurrentPage(), qo.getPageSize());
        }
    }

    @Override
    public void audit(int state, Long id, String remark) {
        //查询出当前审核对象,是否为带审核状态
        RechargeOffline rechargeOffline = rechargeOfflineMapper.selectByPrimaryKey(id);
        if (rechargeOffline != null && rechargeOffline.getState() == BaseAuditDomain.STATE_APPLY) {
            rechargeOffline.setRemark(remark);
            rechargeOffline.setAuditor(UserContext.getCurrent());
            rechargeOffline.setApplyTime(new Date());
            rechargeOffline.setState(state);
            if (state == BaseAuditDomain.STATE_PASS) {//审核通过
                //找到充值用户的账户;
                Account account = this.accountService.get(rechargeOffline.getApplier().getId());
                //增加账户的可用余额;
                account.setUsableAmount(account.getUsableAmount().add(rechargeOffline.getAmount()));
                //给账户添加一条账户流水;
                accountFlowService.addRechargeFlow(rechargeOffline, account);
                //修改账户
                this.accountService.update(account);
            }
        }
        rechargeOfflineMapper.updateByPrimaryKey(rechargeOffline);
    }
}
