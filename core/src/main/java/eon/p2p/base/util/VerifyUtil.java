package eon.p2p.base.util;

import java.util.Random;
import java.util.UUID;

/**
 * 随机产生四位验证码
 */
public class VerifyUtil {


    private static final Random RANDOM = new Random();

    public static final Long VERIFY_TIME = 500l;//验证码验证时间需要在3分钟之内

    /**
     * 产生四位数的验证码
     *
     * @return
     */
    public static Integer getCode() {
        return RANDOM.nextInt(9000) + 1000;

    }
}
