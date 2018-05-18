package eon.p2p.base.domain;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import eon.p2p.base.util.DateUtil;
import eon.p2p.base.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Alias("RealAuth")
public class RealAuth extends BaseDomain {

    public final static int MAN = 0;//男性
    public final static int WOMAN = 1;//女性

    public final static int NOT_AUDIT = 0;//未审核
    public final static int AUDITED = 1;//已审核
    public final static int AUDITED_FAIL = 2;//审核失败

    private Logininfo logininfo;//申请实名认证用户

    private int sex;
    private String idNumber;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthday;
    private String address;
    private String remark;

    private int state;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditTime;
    private String realName;
    private Date applyTime;

    private Logininfo auditor;//审核人

    private String image_front;//身份证正面
    private String image_behind;//身份证背面

    @Override
    public String toString() {
        return "RealAuth{" +
                "sex=" + sex +
                ", idNumber='" + idNumber + '\'' +
                ", birthday=" + birthday +
                ", address='" + address + '\'' +
                ", remark='" + remark + '\'' +
                ", state=" + state +
                ", auditTime=" + auditTime +
                ", realName='" + realName + '\'' +
                ", applyTime=" + applyTime +
                ", image_front='" + image_front + '\'' +
                ", image_behind='" + image_behind + '\'' +
                ", id='" + this.getId() + '\'' +
                '}';
    }

    public String getSexDisplay() {
        return this.sex == 0 ? "男性" : "女性";
    }

    public String getStateDisplay() {
        if (this.state == 0) {
            return "待审核";
        } else if (this.state == 1) {
            return "已审核";
        } else if (this.state == 2) {
            return "审核失败";
        }
        return "";
    }

    public String getJsonString() throws JsonProcessingException {
        Map<String, Object> map = new HashMap<>();
        map.put("username", this.logininfo.getUsername());
        map.put("realName", this.realName);
        map.put("idNumber", this.idNumber);
        map.put("sex", this.getSexDisplay());
        map.put("birthday", DateUtil.formatDate(this.applyTime, "yyyy-MM-dd"));
        map.put("address", this.address);
        map.put("image_front", this.image_front);
        map.put("image_behind", this.image_behind);
        map.put("id", this.getId());
        return new ObjectMapper().writeValueAsString(map);
    }
}
