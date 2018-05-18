package eon.p2p.base.service;

/**
 * 用户发送验证码相关
 */
public interface IVerfiyService {

    /**
     * 发送验证码
     */
    void sendVerifyCode(String phoneNumber);

    /**
     * 验证验证码
     */
    Boolean verify(String phoneNumber, String username);
}
