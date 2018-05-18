package eon.p2p.base.domain;

import eon.p2p.base.util.BidConst;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户流水
 */
@Getter
@Setter
@Alias("AccountFlow")
public class AccountFlow extends BaseDomain {

	private int accountActionType;// 账户流水类型（参考BidConst）
	private BigDecimal amount = BidConst.ZERO; // 发生额(流水账金额,ZERO_AMOUNT:0)
	private Account account; // 对应的账户
	private String note; // 备注
	private BigDecimal balance = BidConst.ZERO; // 可用余额
	private BigDecimal freezed = BidConst.ZERO;// 冻结金额
	private Date actionTime; // 发生时间
}
