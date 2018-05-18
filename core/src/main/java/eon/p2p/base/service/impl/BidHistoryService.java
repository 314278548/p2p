package eon.p2p.base.service.impl;

import eon.p2p.base.domain.BidRequestAuditHistory;
import eon.p2p.base.mapper.BidRequestAuditHistoryMapper;
import eon.p2p.base.query.BidRequestAuditHistoryQueryObject;
import eon.p2p.base.service.IBidHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BidHistoryService implements IBidHistoryService {
    @Autowired
    private BidRequestAuditHistoryMapper bidRequestAuditHistoryMapper;

    @Override
    public int insert(BidRequestAuditHistory record) {
        return bidRequestAuditHistoryMapper.insert(record);
    }

    @Override
    public BidRequestAuditHistory selectByPrimaryKey(Long id) {
        return bidRequestAuditHistoryMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<BidRequestAuditHistory> selectByBid(Long id) {
        return bidRequestAuditHistoryMapper.selectByBid(id);
    }

}
