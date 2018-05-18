package eon.p2p.base.service.impl;

import eon.p2p.base.domain.IpLog;
import eon.p2p.base.mapper.IplogMapper;
import eon.p2p.base.query.IpLogQueryObejct;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.IIpLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IpLogService implements IIpLogService {
    @Autowired
    private IplogMapper iplogMapper;

    @Override
    public PageResult<IpLog> query(IpLogQueryObejct qo) {
        Long count = iplogMapper.count(qo);
        //数据为0时
        if (count == 0) {
            return PageResult.EMPTY;
        }
        return new PageResult<>(iplogMapper.selectAll(qo), count, qo.getCurrentPage(), qo.getPageSize());
    }
}
