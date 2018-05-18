package eon.p2p.base.service.impl;

import eon.p2p.base.domain.Account;
import eon.p2p.base.domain.Bid;
import eon.p2p.base.domain.BidRequest;
import eon.p2p.base.mapper.BidMapper;
import eon.p2p.base.service.IAccountFlowService;
import eon.p2p.base.service.IAccountService;
import eon.p2p.base.service.IBidRequestService;
import eon.p2p.base.service.IBidService;
import eon.p2p.base.util.BidConst;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Service
public class BidService implements IBidService {
    @Autowired
    private BidMapper bidMapper;
    @Autowired
    private IBidRequestService bidRequestService;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IAccountFlowService accountFlowService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return bidMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Bid record) {
        return bidMapper.insert(record);
    }

    @Override
    public Bid selectByPrimaryKey(Long id) {
        return bidMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Bid> selectAll() {
        return bidMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Bid record) {
        return bidMapper.updateByPrimaryKey(record);
    }

    @Override
    public void bid(Long bidRequestId, BigDecimal amount){
        BidRequest bidRequest = bidRequestService.get(bidRequestId);
        Account currentAccount = accountService.getCurrent();
        if (bidRequest != null//借款存在
                && bidRequest.getBidRequestState() == BidConst.BIDREQUEST_STATE_BIDDING//为招标状态
                && !bidRequest.getCreateUser().getId().equals(currentAccount.getId())//投标人不为借款人
                && currentAccount.getUsableAmount().compareTo(amount) >= 0//当前可用余额大于投标金额
                && amount.compareTo(bidRequest.getMinBidAmount()) >= 0//投标金额>=最小投标金额
                && amount.compareTo(bidRequest.getRemainAmount()) <= 0) {//投标金额<=借款剩余投标金额
            //通过验证,执行投标
            //创建投标对象,设置属性
            Bid bid = new Bid();
            bid.setActualRate(bidRequest.getCurrentRate());
            bid.setAvailableAmount(amount);
            bid.setBidRequestId(bidRequestId);
            bid.setBidRequestTitle(bidRequest.getTitle());
            bid.setBidTime(new Date());
            bid.setBidUser(UserContext.getCurrent());
            bidMapper.insert(bid);//插入投标信息

            //更改账户信息
            currentAccount.setUsableAmount(currentAccount.getUsableAmount().subtract(amount));//可用余额减少
            currentAccount.setFreezedAmount(currentAccount.getFreezedAmount().add(amount));//冻结金额增加
            //跟新账户操作
            accountService.update(currentAccount);
            //生成流水
            accountFlowService.createBid(bid, currentAccount);
            //修改借款相关信息
            bidRequest.setBidCount(bidRequest.getBidCount() + 1);
            bidRequest.setCurrentSum(bidRequest.getCurrentSum().add(amount));
            //判断标是否投满
            if (bidRequest.getBidRequestAmount().equals(bidRequest.getCurrentSum())) {
                bidRequest.setBidRequestState(BidConst.BIDREQUEST_STATE_APPROVE_PENDING_1);//进入满标已审
            }
            bidRequestService.update(bidRequest);
        }
    }
}
