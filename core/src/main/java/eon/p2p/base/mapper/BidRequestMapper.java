package eon.p2p.base.mapper;

import eon.p2p.base.domain.BidRequest;
import eon.p2p.base.query.BidRequestQueryObejct;

import java.util.List;

public interface BidRequestMapper {

    int insert(BidRequest record);

    BidRequest selectByPrimaryKey(Long id);


    int updateByPrimaryKey(BidRequest record);

    Long queryForCount(BidRequestQueryObejct qo);

    List<BidRequest> query(BidRequestQueryObejct qo);
}