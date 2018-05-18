package eon.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 借款审核历史对象
 * 
 * @author Administrator
 * 
 */
@Getter
@Setter
public class BidRequestAuditHistory extends BaseAuditDomain {

	private static final long serialVersionUID = -3069574548606283953L;
	public static final int AUDIT_TYPE_PUBLISH = 0;
	public static final int AUDIT_TYPE_FULL_1 = 1;
	public static final int AUDIT_TYPE_FULL_2 = 2;

	private Long bidRequestId;
	private int auditType;

	public String getAuditTypeDisplay() {
		switch (auditType) {
		case AUDIT_TYPE_PUBLISH:
			return "发标前审核";
		case AUDIT_TYPE_FULL_1:
			return "满标一审";
		case AUDIT_TYPE_FULL_2:
			return "满标二审";
		default:
			return "异常";
		}
	}

}
