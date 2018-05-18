package eon.p2p.base.domain;

import com.sun.org.apache.xpath.internal.operations.Bool;
import eon.p2p.base.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 登录日志
 */
@Getter
@Setter
public class IpLog extends BaseDomain {

    public final static Boolean LOGIN_SUCCESS = true;//登录成功
    public final static Boolean LOGIN_FAIL = false;//登录失败

    private String ip;
    private String username;
    private Boolean state;
    private Date loginTime;
    private int userType;//用户类型

    public String getStateByView() {
        return this.state ? "成功" : "失败";
    }

    public String getUserTypeOfVeiw() {
        return userType == 1 ? "后端用户" : "前端用户";
    }

    public String getUsername() {
        return StringUtil.hasLength(username) ? username : null;
    }

}
