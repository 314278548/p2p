package eon.p2p.base.service;


import eon.p2p.base.domain.Bid;

import java.math.BigDecimal;
import java.util.List;

public interface IBidService {
    int deleteByPrimaryKey(Long id);

    int insert(Bid record);

    Bid selectByPrimaryKey(Long id);

    List<Bid> selectAll();

    int updateByPrimaryKey(Bid record);

    void bid(Long bidRequestId, BigDecimal amount);
}
