package eon.p2p.base.service.impl;

import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.domain.UserInfo;
import eon.p2p.base.domain.VedioAuth;
import eon.p2p.base.mapper.UserInfoMapper;
import eon.p2p.base.mapper.VedioAuthMapper;
import eon.p2p.base.query.VedioQueryObject;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.IVedioAuthService;
import eon.p2p.base.util.BitStatesUtils;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

;

@Service
public class VeidoAuthService implements IVedioAuthService {

    @Autowired
    private VedioAuthMapper vedioAuthMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public int insert(VedioAuth record) {
        return vedioAuthMapper.insert(record);
    }

    @Override
    public PageResult<VedioAuth> query(VedioQueryObject qo) {
        Long count = vedioAuthMapper.count(qo);
        if (count == 0) {
            return PageResult.EMPTY;
        }
        return new PageResult(vedioAuthMapper.query(qo), count, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public void audit(String remark, int state, Long applierId) {
        VedioAuth vedioAuth = new VedioAuth();
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(applierId);
        if (userInfo != null && !userInfo.isBindVedioAuth()) {
            vedioAuth.setRemark(remark);
            vedioAuth.setState(state);
            Logininfo logininfo = new Logininfo();
            logininfo.setId(applierId);
            vedioAuth.setApplier(logininfo);
            vedioAuth.setApplyTime(new Date());
            vedioAuth.setAuditTime(new Date());
            vedioAuth.setAuditor(UserContext.getCurrent());
            vedioAuthMapper.insert(vedioAuth);
            if (state == 1) {
                userInfo.addState(BitStatesUtils.OP_VEDIO_AUTH);
                userInfoMapper.updateByPrimaryKey(userInfo);
            }
        } else {
            throw new RuntimeException("当前用户已视频认证");
        }
    }
}
