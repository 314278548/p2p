package eon.p2p.base.service.impl;

import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.domain.RealAuth;
import eon.p2p.base.domain.UserInfo;
import eon.p2p.base.mapper.LogininfoMapper;
import eon.p2p.base.mapper.RealAuthMapper;
import eon.p2p.base.query.RealAuthQueryObject;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.IRealAuthService;
import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.util.BitStatesUtils;
import eon.p2p.base.util.FileUtil;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Service
public class RealAuthService implements IRealAuthService {

    @Autowired
    private RealAuthMapper realAuthMapper;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private LogininfoMapper logininfoMapper;

    @Override
    public RealAuth get(Long id) {
        return realAuthMapper.getOne(id);
    }

    @Override
    public String upload(MultipartFile file) {
        String upload = FileUtil.upload(file);
        return upload.substring(upload.lastIndexOf("\\") + 1);
    }

    @Override
    public void apply(RealAuth realAuth) {
        UserInfo current = userInfoService.getCurrent();
        //没有审核且当前用户对象上没有待审核的实名对象
        if (!current.isBindAuth() && current.getRealAuthId() == null) {
            realAuth.setState(RealAuth.NOT_AUDIT);
            realAuth.setLogininfo(UserContext.getCurrent());
            realAuth.setApplyTime(new Date());
            realAuthMapper.insert(realAuth);
            current.setRealAuthId(realAuth.getId());
            userInfoService.update(current);
        } else {
            throw new RuntimeException("提交失败");
        }
    }

    @Override
    public PageResult<RealAuth> query(RealAuthQueryObject qo) {
        Long count = realAuthMapper.count(qo);
        if (count == 0) {
            return PageResult.EMPTY;
        } else {
            return new PageResult<>(this.realAuthMapper.query(qo), count, qo.getCurrentPage(), qo.getPageSize());
        }
    }

    @Override
    public void update(RealAuth r) {
        this.realAuthMapper.update(r);
    }

    @Override
    public void audit(Long id, int state, String remark) {
        RealAuth realAuth = this.get(id);
        //审核对象不为空切状态为待审核
        if (realAuth != null && realAuth.getState() == RealAuth.NOT_AUDIT) {
            UserInfo userInfo = userInfoService.get(realAuth.getLogininfo().getId());
            //设置属性
            realAuth.setAuditor(UserContext.getCurrent());
            realAuth.setAuditTime(new Date());
            realAuth.setRemark(remark);
            realAuth.setState(state);
            //审核通过
            if (state == RealAuth.AUDITED) {
                userInfo.addState(BitStatesUtils.OP_REAL_AUTH);//实名认证状态吗
                userInfo.setRealName(realAuth.getRealName());
                userInfo.setIdNumber(realAuth.getIdNumber());
            } else {
                userInfo.setRealAuthId(null);//审核不通过消除关联关系,使用户可以进行再次提交审核
            }
            this.userInfoService.update(userInfo);
            this.realAuthMapper.update(realAuth);
        } else {
            throw new RuntimeException("审核失败,请联系管理员");
        }
    }

    @Override
    public List<Logininfo> listByKeyword(String keyword) {
        return logininfoMapper.listByKeyword(keyword);
    }
}
