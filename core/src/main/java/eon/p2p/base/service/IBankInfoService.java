package eon.p2p.base.service;

import eon.p2p.base.domain.BankInfo;
import eon.p2p.base.query.BankInfoQueryObject;
import eon.p2p.base.query.page.PageResult;

/**
 * 后台公司银行账户相关
 */
public interface IBankInfoService {


    PageResult<BankInfo> query(BankInfoQueryObject qo);

    void saveOrUpdate(BankInfo bankInfo);
}
