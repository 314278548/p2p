package eon.p2p.base.service.impl;

import eon.p2p.base.domain.BankInfo;
import eon.p2p.base.mapper.BankInfoMapper;
import eon.p2p.base.query.BankInfoQueryObject;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.IBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BankInfoService implements IBankInfoService {
    @Autowired
    private BankInfoMapper bankInfoMapper;

    @Override
    public PageResult<BankInfo> query(BankInfoQueryObject qo) {
        Long count = bankInfoMapper.count(qo);
        if (count == 0) {
            return PageResult.EMPTY;
        } else {
            return new PageResult<>(bankInfoMapper.query(qo), count, qo.getCurrentPage(), qo.getPageSize());
        }
    }

    @Override
    public void saveOrUpdate(BankInfo bankInfo) {
        if (bankInfo.getId() != null) {
            this.bankInfoMapper.updateByPrimaryKey(bankInfo);
        } else {
            this.bankInfoMapper.insert(bankInfo
            );
        }
    }
}
