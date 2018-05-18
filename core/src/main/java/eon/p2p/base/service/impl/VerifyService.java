package eon.p2p.base.service.impl;

import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.service.IVerfiyService;
import eon.p2p.base.util.DateUtil;
import eon.p2p.base.util.SendCode;
import eon.p2p.base.util.UserContext;
import eon.p2p.base.util.VerifyUtil;
import eon.p2p.base.vo.VerifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class VerifyService implements IVerfiyService {

    @Autowired
    private IUserInfoService userInfoService;

    @Override
    public void sendVerifyCode(String phoneNumber) {
        VerifyCode currentVerifyCode = UserContext.getCurrentVerifyCode();
        //为null时为第一次发送  第一次发送时间和第二次发送时间相差大于九十秒
        Date currentTime = new Date();
        if (currentVerifyCode == null || DateUtil.secondsBetween(currentTime, currentVerifyCode.getLastSendTime()) > 90) {
            try {
                String code = VerifyUtil.getCode().toString();
                SendCode.sendCode(phoneNumber, code);
                //发送短信
                VerifyCode verifyCode = new VerifyCode();
                verifyCode.setLastSendTime(currentTime);
                verifyCode.setPhoneNumber(phoneNumber);
                verifyCode.setVerifyCode(code);
                //设置到session中
                UserContext.setCurrentVerifyCode(verifyCode);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException("发送失败!");
            }
        } else {
            throw new RuntimeException("发送验证码太频繁!");
        }
    }

    @Override
    public Boolean verify(String phoneNumber, String verifyCode) {
        VerifyCode currentVerifyCode = UserContext.getCurrentVerifyCode();
        if (currentVerifyCode != null &&//发送了验证码
                currentVerifyCode.getPhoneNumber().equals(phoneNumber)//需绑定的手机号与收到短信的手机号码一致
                && currentVerifyCode.getVerifyCode().equals(verifyCode)//验证码一致
                && DateUtil.secondsBetween(new Date(), currentVerifyCode.getLastSendTime()) <= VerifyUtil.VERIFY_TIME) {//在三分钟之内验证验证码
            return true;
        } else {
            return false;
        }
    }
}