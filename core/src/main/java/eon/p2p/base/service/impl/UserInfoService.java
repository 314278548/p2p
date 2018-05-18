package eon.p2p.base.service.impl;

import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.domain.UserInfo;
import eon.p2p.base.mapper.UserInfoMapper;
import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.service.IVerfiyService;
import eon.p2p.base.util.BitStatesUtils;
import eon.p2p.base.util.StringUtil;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements IUserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private IVerfiyService verfiyService;

    @Override
    public void update(UserInfo userInfo) {
        int i = userInfoMapper.updateByPrimaryKey(userInfo);
        //乐观锁,操作影响数返回为0,表示更新失败
        if (i == 0) {
            throw new RuntimeException("乐观锁失败,userInfo:" + userInfo.getId());
        }
    }

    @Override
    public void add(UserInfo userInfo) {
        this.userInfoMapper.insert(userInfo);
    }

    @Override
    public UserInfo get(Long id) {
        return this.userInfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void bindPhone(String phoneNubmer, String verfiCode) {
        UserInfo current = this.get(UserContext.getCurrent().getId());
        //当前用户没有绑定手机并且手机号为空
        if (!current.isBindPhone() && !StringUtil.hasLength(current.getPhoneNumber())) {
            //进行验证码验证
            boolean verify = verfiyService.verify(phoneNubmer, verfiCode);
            //合法
            if (verify) {
                //绑定,添加状态码,录入手机号
                current.addState(BitStatesUtils.OP_BIND_PHONE);
                current.setPhoneNumber(phoneNubmer);
                this.update(current);
            } else {
                throw new RuntimeException("验证码验证失败");
            }
        } else {
            throw new RuntimeException("此用户已绑定手机号");
        }
    }

    @Override
    public Boolean phoneIsUsed(String phoneNumber) {
        return userInfoMapper.judgePhoneIsBind(phoneNumber) == 0 ? false : true;
    }

    @Override
    public UserInfo getCurrent() {
        return this.get(UserContext.getCurrent().getId());
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {
        UserInfo current = this.getCurrent();
        current.setEducationBackground(userInfo.getEducationBackground());
        current.setHouseCondition(userInfo.getHouseCondition());
        current.setIncomeGrade(userInfo.getIncomeGrade());
        current.setKidCount(userInfo.getKidCount());
        current.setMarriage(userInfo.getMarriage());
        if (!current.isBindInfo()) {
            current.addState(BitStatesUtils.OP_BASIC_INFO);//添加绑定基本信息的状态码
        }
        update(current);
    }

}
