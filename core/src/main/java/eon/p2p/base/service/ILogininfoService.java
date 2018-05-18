package eon.p2p.base.service;

import eon.p2p.base.domain.Logininfo;

public interface ILogininfoService {
    /**
     * 用户注册
     *
     * @param username
     * @param password
     */
    void register(String username, String password);


    /**
     * 检查用户名是否存在,
     * 如果存在返回true,
     * 否则返回false
     *
     * @param username
     * @return
     */
    Long selectCountByUsername(String username);

    /**
     * 用户登录
     *
     * @param username
     * @param password
     */
    Logininfo login(String username, String password, String ip, int userType);

    /**
     * 初始化第一个后台管理员
     */
    void initAdmin();
}
