package eon.p2p.base.domain;

import eon.p2p.base.util.BidConst;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * 用户对应的账户对象
 */
@Getter
@Setter
public class Account extends BaseDomain {

    public final static BigDecimal MIN_BID_AMOUNT = new BigDecimal("100.00");

    private int version;
    private String tradePassword;//交易密码
    private BigDecimal usableAmount = BidConst.ZERO;//可用金额
    private BigDecimal freezedAmount = BidConst.ZERO;//冻结金额
    private BigDecimal unReceiveInterest = BidConst.ZERO;//待收利息
    private BigDecimal unReceivePrincipal = BidConst.ZERO;//待收本金
    private BigDecimal unReturnAmount = BidConst.ZERO;//待还金额
    private BigDecimal remainBorrowLimit = BidConst.INIT_BORROW_LIMIT;//账户剩余授信金额
    private BigDecimal borrowLimit = BidConst.INIT_BORROW_LIMIT;//授信金额

    public BigDecimal getTotalAmount() {
        return this.usableAmount.add(this.freezedAmount).add(this.unReceivePrincipal);
    }

    public String getMinBidAmount() {
        return this.MIN_BID_AMOUNT.toString();
    }

    public String getRemainBorrowLimit() {
        return this.remainBorrowLimit.toString();
    }


}
