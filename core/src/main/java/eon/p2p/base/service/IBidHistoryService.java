package eon.p2p.base.service;

import eon.p2p.base.domain.BidRequestAuditHistory;
import eon.p2p.base.query.BidRequestAuditHistoryQueryObject;

import java.util.List;

public interface IBidHistoryService {
    int insert(BidRequestAuditHistory record);

    BidRequestAuditHistory selectByPrimaryKey(Long id);


    List<BidRequestAuditHistory> selectByBid(Long id);
}
