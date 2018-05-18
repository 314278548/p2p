package eon.p2p.base.mapper;

import eon.p2p.base.domain.BidRequestAuditHistory;
import eon.p2p.base.query.BidRequestAuditHistoryQueryObject;

import java.util.List;

public interface BidRequestAuditHistoryMapper {

	int insert(BidRequestAuditHistory record);

	BidRequestAuditHistory selectByPrimaryKey(Long id);

	int queryForCount(BidRequestAuditHistoryQueryObject qo);

	List<BidRequestAuditHistory> query(BidRequestAuditHistoryQueryObject qo);

	List<BidRequestAuditHistory> selectByBid(Long id);
}