package eon.p2p.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 验证码vo类
 */
@Getter
@Setter
public class VerifyCode {
    private String phoneNumber;//手机号
    private String verifyCode;//验证码
    private Date lastSendTime;//最后发送时间
}
