package eon.p2p.base.domain;

/**
 * 登录相关信息
 */
public class Logininfo extends BaseDomain {


    public static final int USERTYPE_NORMAL = 0;//前段用户
    public static final int USERTYPE_SYSTEM = 1;//后台用户
    public static final int STATE_NORMAL = 0;
    public static final int STATE_LOCK = 1;
    public static final int STATE_DELETE = -1;

    private int userType;//用户类型
    private String username;

    private String password;

    private int state;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "Logininfo{" +
                "userType=" + userType +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", state=" + state +
                '}';
    }
}