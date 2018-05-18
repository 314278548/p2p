package eon.p2p.base.service;

import eon.p2p.base.domain.VedioAuth;
import eon.p2p.base.query.VedioQueryObject;
import eon.p2p.base.query.page.PageResult;

/**
 * 视屏认证相关服务
 */
public interface IVedioAuthService {

    int insert(VedioAuth record);

    PageResult<VedioAuth> query(VedioQueryObject qo);


    void audit(String remark, int state, Long applierId);
}
