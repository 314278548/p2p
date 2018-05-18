package eon.p2p.base.service.impl;

import eon.p2p.base.domain.Account;
import eon.p2p.base.domain.IpLog;
import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.domain.UserInfo;
import eon.p2p.base.mapper.IplogMapper;
import eon.p2p.base.mapper.LogininfoMapper;
import eon.p2p.base.service.IAccountService;
import eon.p2p.base.service.ILogininfoService;
import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.util.MD5;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import java.util.Date;

@Service
public class LogininfoService implements ILogininfoService {
    @Autowired
    private LogininfoMapper logininfoMapper;
    @Autowired
    private IAccountService accountService;
    @Autowired
    private IUserInfoService userInfoService;
    @Autowired
    private IplogMapper iplogMapper;

    @Override
    public void register(String username, String password) {
        Long count = logininfoMapper.selectCountByUsername(username);
        if (count > 0) {
            throw new RuntimeException("用户名已存在");
        }
        Logininfo logininfo = new Logininfo();
        logininfo.setUsername(username);
        logininfo.setState(Logininfo.STATE_NORMAL);//注册为前端用户
        logininfo.setPassword(MD5.encode(password));
        logininfo.setUserType(Logininfo.USERTYPE_NORMAL);
        logininfoMapper.insert(logininfo);

        //初始账户信息和用户信息
        Account account = new Account();
        account.setId(logininfo.getId());
        accountService.add(account);

        UserInfo userInfo = new UserInfo();
        userInfo.setId(logininfo.getId());
        userInfoService.add(userInfo);
    }

    @Override
    public Long selectCountByUsername(String username) {
        return logininfoMapper.selectCountByUsername(username);
    }

    /**
     * 后台用户登录
     *
     * @param username
     * @param password
     * @param ip
     * @param userType
     * @return
     */
    @Override
    public Logininfo login(String username, String password, String ip, int userType) {
        //登录日志对象
        IpLog ipLog = new IpLog();
        ipLog.setLoginTime(new Date());
        ipLog.setUsername(username);
        ipLog.setIp(ip);
        ipLog.setUserType(userType);
        Logininfo current = logininfoMapper.login(username, MD5.encode(password), userType);
        if (current != null) {
            //成功登录,存入session
            UserContext.setCurrent(current);
            ipLog.setState(IpLog.LOGIN_SUCCESS);
        } else {
            //登录失败
            ipLog.setState(IpLog.LOGIN_FAIL);
        }
        iplogMapper.insert(ipLog);
        return current;
    }

    @Override
    public void initAdmin() {
        Long count = logininfoMapper.countByUserType(Logininfo.USERTYPE_SYSTEM);
        //无管理员
        if (count == 0) {
            Logininfo admin = new Logininfo();
            admin.setUsername("admin");
            admin.setPassword(MD5.encode("root"));
            admin.setUserType(Logininfo.USERTYPE_SYSTEM);
            admin.setState(Logininfo.STATE_NORMAL);
            logininfoMapper.insert(admin);
        }
    }

}
