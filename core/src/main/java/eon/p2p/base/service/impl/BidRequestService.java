package eon.p2p.base.service.impl;

import eon.p2p.base.domain.*;
import eon.p2p.base.mapper.BidRequestAuditHistoryMapper;
import eon.p2p.base.mapper.BidRequestMapper;
import eon.p2p.base.query.BidRequestQueryObejct;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.IAccountService;
import eon.p2p.base.service.IBidRequestService;
import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.util.*;
import org.apache.http.client.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class BidRequestService implements IBidRequestService {
    @Autowired
    private BidRequestMapper bidRequestMapper;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private BidRequestAuditHistoryMapper bidRequestAuditHistoryMapper;

    @Override
    public void update(BidRequest bidRequest) {
        int i = bidRequestMapper.updateByPrimaryKey(bidRequest);
        if (i == 0) {
            throw new RuntimeException("乐观锁失败 bidrequest");
        }
    }

    /**
     * 查看是否能够申请借款
     *
     * @param id
     * @return
     */
    @Override
    public boolean canApply(Long id) {
        UserInfo userInfo = userInfoService.get(id);
        if (userInfo != null && userInfo.isBindInfo() &&
                userInfo.isBindVedioAuth()
                && userInfo.isBindAuth()
                && userInfo.getScore() >= UserInfo.BASE_SCORE
                && !userInfo.hasBidRequestProcess()) {
            return true;
        }
        return false;
    }

    /**
     * 申请借款
     *
     * @param bidRequest
     */
    @Override
    public void apply(BidRequest bidRequest) {
        Logininfo current = UserContext.getCurrent();
        Account account = this.accountService.get(current.getId());
        //再次检查当前用户是否能够发标
        if (canApply(current.getId())
                && bidRequest.getBidRequestAmount().compareTo(
                new BigDecimal(account.getRemainBorrowLimit())) <= 0) {//借款金额小于等于授信额度
            bidRequest.setReturnType(BidConst.BIDREQUEST_TYPE_NORMAL);//普通信用标
            bidRequest
                    .setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_PENDING);
            //设置总回报金额
            bidRequest
                    .setTotalRewardAmount(CalculatetUtil.calTotalInterest(
                            bidRequest.getReturnType(),
                            bidRequest.getBidRequestAmount(),
                            bidRequest.getCurrentRate(),
                            bidRequest.getMonthes2Return()));
            bidRequest.setCreateUser(current);
            bidRequest.setApplyTime(new Date());
            this.bidRequestMapper.insert(bidRequest);
            UserInfo userinfo = this.userInfoService.get(current.getId());
            userinfo.addState(BitStatesUtils.OP_HAS_BIDRQUEST);//给用户设置借款状态
            this.userInfoService.update(userinfo);
        }
    }

    @Override
    public PageResult<BidRequest> query(BidRequestQueryObejct qo) {
        Long count = bidRequestMapper.queryForCount(qo);
        //数据为0时
        if (count == 0) {
            return PageResult.EMPTY;
        }
        return new PageResult<>(bidRequestMapper.query(qo), count, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void audit(Long id, int state, String remark) {
        //检查状态
        BidRequest bidRequest = this.bidRequestMapper.selectByPrimaryKey(id);
        if (bidRequest.getBidRequestState() == BidConst.BIDREQUEST_STATE_PUBLISH_PENDING) {//当前标为待审核状态
            UserInfo userInfo = userInfoService.get(bidRequest.getCreateUser().getId());
            //创建标的审核历史对象;
            BidRequestAuditHistory history = new BidRequestAuditHistory();
            history.setApplier(bidRequest.getCreateUser());
            history.setApplyTime(bidRequest.getApplyTime());
            history.setAuditor(UserContext.getCurrent());
            history.setAuditTime(new Date());
            history.setRemark(remark);
            history.setState(state);
            history.setBidRequestId(id);
            history.setAuditType(BidRequestAuditHistory.AUDIT_TYPE_PUBLISH);//这是发标审核
            this.bidRequestAuditHistoryMapper.insert(history);
            //审核通过
            if (state == BidRequestAuditHistory.STATE_PASS) {
                //修改标状态
                bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_BIDDING);//发标
                bidRequest.setNote(remark);
                bidRequest.setPublishTime(new Date());
                bidRequest.setDisableDate(DateUtil.addMonths(new Date(), bidRequest.getDisableDays()));
                //修改用户状态

            } else {
                //审核失败:修改标的状态,去掉用户的状态码
                bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_PUBLISH_REFUSE);//拒绝发标
            }
            userInfo.removeState(BitStatesUtils.OP_HAS_BIDRQUEST);
            userInfoService.update(userInfo);
            bidRequestMapper.updateByPrimaryKey(bidRequest);
        }
    }

    @Override
    public BidRequest get(Long id) {
        return bidRequestMapper.selectByPrimaryKey(id);
    }

    /**
     * 当满标一审拒绝,满标二审拒绝,流标和取消借款,都要把标的的投标人的钱返回
     * @param bidRequest
     */
//    private void returnMoney(BidRequest bidRequest) {
//        Map<Long, Account> updateAccounts = new HashMap<>();
//        //遍历投标;
//        for (Bid bid : bidRequest.getBids()) {
//            //得到投标人;
//            Long bidUserId = bid.getBidUser().getId();
//            Account bidAccount = updateAccounts.get(bidUserId);
//            if (bidAccount == null) {
//                bidAccount = this.accountService.get(bidUserId);
//                updateAccounts.put(bidUserId, bidAccount);
//            }
//            //修改投资人的账户信息;
//            bidAccount.addUseableAmount(bid.getAvailableAmount());
//            bidAccount.addFreezedAmount(bid.getAvailableAmount().negate());
//            //生成投标退款的流水
//            this.accountFlowService.addReturnBidMoneyFlow(bid, bidAccount);
//        }
//        for (Account account : updateAccounts.values()) {
//            //修改账户
//            this.accountService.update(account);
//        }
//    }

}
