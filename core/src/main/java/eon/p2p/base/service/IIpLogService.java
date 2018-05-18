package eon.p2p.base.service;

import eon.p2p.base.domain.IpLog;
import eon.p2p.base.query.IpLogQueryObejct;
import eon.p2p.base.query.page.PageResult;

/**
 * 登录日志相关
 */
public interface IIpLogService {
    /**
     * 查询日志对象
     *
     * @param qo
     * @return
     */
    PageResult<IpLog> query(IpLogQueryObejct qo);
}
