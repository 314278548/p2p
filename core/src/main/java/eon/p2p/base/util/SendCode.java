package eon.p2p.base.util;

import com.alibaba.fastjson.JSON;
import eon.p2p.base.domain.Logininfo;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class SendCode {

    private static  Properties properties;

    static {
        ClassLoader cs = Thread.currentThread().getContextClassLoader();
        properties = new Properties();
        try (InputStream in = cs.getResourceAsStream("sms.properties")) {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //发送验证码的请求路径URL
    private static final String
            SERVER_URL = properties.getProperty("sms.url");
    //网易云信分配的账号，请替换你在管理后台应用下申请的Appkey
    private static final String
            APP_KEY = properties.getProperty("sms.key");
    //网易云信分配的密钥，请替换你在管理后台应用下申请的appSecret
    private static final String APP_SECRET = properties.getProperty("sms.secret");
    //随机数
    private static final String NONCE = properties.getProperty("sms.nonce");
    //短信模板ID
    private static final String TEMPLATEID_OF_PHONE = "4052686";
    private static final String TEMPLATEID_OF_SMS = properties.getProperty("sms.templateID");

    public static void sendCode(String phoneNubmer, String verifyCode) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(SERVER_URL);
        String curTime = String.valueOf((new Date()).getTime() / 1000L);
        /*
         * 参考计算CheckSum的java代码，在上述文档的参数列表中，有CheckSum的计算文档示例
         */
        String checkSum = CheckSumBuilder.getCheckSum(APP_SECRET, NONCE, curTime);

        // 设置请求的header
        httpPost.addHeader("AppKey", APP_KEY);
        httpPost.addHeader("Nonce", NONCE);
        httpPost.addHeader("CurTime", curTime);
        httpPost.addHeader("CheckSum", checkSum);
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的的参数，requestBody参数
        List<NameValuePair> nvps = new ArrayList<>();
        /*
         * 1.如果是模板短信，请注意参数mobile是有s的，详细参数配置请参考“发送模板短信文档”
         * 2.参数格式是jsonArray的格式，例如 "['13888888888','13666666666']"
         * 3.params是根据你模板里面有几个参数，那里面的参数也是jsonArray格式
         */
        nvps.add(new

                BasicNameValuePair("templateid", TEMPLATEID_OF_SMS));
        nvps.add(new

                BasicNameValuePair("mobile", phoneNubmer));
        nvps.add(new

                BasicNameValuePair("authCode", verifyCode));
        httpPost.setEntity(new

                UrlEncodedFormEntity(nvps, "utf-8"));

        // 执行请求
        HttpResponse response = httpClient.execute(httpPost);
        /*
         * 1.打印执行结果，打印结果一般会200、315、403、404、413、414、500
         * 2.具体的code有问题的可以参考官网的Code状态表
         */
        String params = EntityUtils.toString(response.getEntity(), "utf-8");
        String state = (String) JSON.parseObject(params).get("code").toString();
        if (!"200".equals(state)) {
            throw new RuntimeException("发送失败!");
        }
    }
}