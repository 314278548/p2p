package eon.p2p.base.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.stereotype.Component;

/**
 * 返回json格式结果
 */
@Getter
@Setter
@ToString
public class AjaxResult {
    private boolean success;
    private String msg;

    /**
     * success 默认为true
     *
     * @param msg
     */
    public AjaxResult(String msg) {
        this(true, msg);
    }

    public AjaxResult(boolean success, String msg) {
        this.success = success;
        this.msg = msg;
    }

}
