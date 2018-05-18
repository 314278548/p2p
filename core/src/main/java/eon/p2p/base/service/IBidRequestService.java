package eon.p2p.base.service;

import eon.p2p.base.domain.BidRequest;
import eon.p2p.base.query.BidRequestQueryObejct;
import eon.p2p.base.query.page.PageResult;

/**
 * 借款相关
 */
public interface IBidRequestService {
    void update(BidRequest bidRequest);

    boolean canApply(Long id);


    void apply(BidRequest bidRequest);

    PageResult<BidRequest> query(BidRequestQueryObejct qo);

    void audit(Long id,int state,String remark);

    BidRequest get(Long id);
}
