package eon.p2p.base.mapper;

import eon.p2p.base.domain.BankInfo;
import eon.p2p.base.query.BankInfoQueryObject;

import java.util.List;

public interface BankInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BankInfo record);

    BankInfo selectByPrimaryKey(Long id);

    List<BankInfo> selectAll();

    int updateByPrimaryKey(BankInfo record);

    Long count(BankInfoQueryObject qo);

    List<BankInfo> query(BankInfoQueryObject qo);
}