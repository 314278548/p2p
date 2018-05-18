package eon.p2p.base.service;

import eon.p2p.base.domain.UserInfo;

/**
 * 用户相关服务
 */
public interface IUserInfoService {

    /**
     * 更新操作,乐观锁
     *
     * @param userInfo
     */
    void update(UserInfo userInfo);

    /**
     * 添加用户信息
     *
     * @param userInfo
     */
    void add(UserInfo userInfo);

    /**
     * 获取用户信息
     *
     * @param id
     * @return
     */
    UserInfo get(Long id);

    /**
     * 绑定手机号
     *
     * @param phoneNumber
     * @param verifyCode
     */
    void bindPhone(String phoneNumber, String verifyCode);

    /**
     * 验证手机号码是否已经绑定过了
     *
     * @param phoneNumber
     */
    Boolean phoneIsUsed(String phoneNumber);

    /**
     * 获取当前用户信息对象
     * @return
     */
    UserInfo getCurrent();

    void updateUserInfo(UserInfo userInfo);
}
