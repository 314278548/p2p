package eon.p2p.base.util;

/**
 * 字符串工具类
 */
public class StringUtil {

    /**
     * 判断字符串是否有长度
     *
     * @param string
     * @return
     */
    public static boolean hasLength(String string) {
        return string == null || "".equals(string.trim()) ? false : true;
    }
}
