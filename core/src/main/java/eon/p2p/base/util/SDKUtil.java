package eon.p2p.base.util;

import java.util.HashMap;
import java.util.Map;

public class SDKUtil {

    public static void post() {

        final String userName = "注册用户名";
        final String key = "接口鉴权KEY";
        final String mobileNos = "要发送的手机号码,多个用逗号隔开";
        final String content = "验证码：8888";
        //https协议请改用https://api.smschn.cn/ 短信接口
        final String url = "http://api.smschn.cn/";
        String a = "http://api.smschn.cn/?user=用户名&key=接口鉴权KEY&mobile=手机号码&content=短信内容";
    }
}
