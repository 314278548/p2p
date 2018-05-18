package eon.p2p.base.domain;

import com.alibaba.fastjson.JSONObject;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class BankInfo extends BaseDomain {

    private String bankName;//银行名称

    private String accountName;//账户账号

    private String bankNumber;//银行代码

    private String bankForkName;//支行名称

    private String iconCls;//图表class

    public String getJsonString(){
        Map<String,Object> m=new HashMap<>();
        m.put("bankName", bankName);
        m.put("accountName", accountName);
        m.put("bankNumber", bankNumber);
        m.put("bankForkName", bankForkName);
        return JSONObject.toJSONString(m);
    }

}