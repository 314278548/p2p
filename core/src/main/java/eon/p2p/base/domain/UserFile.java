package eon.p2p.base.domain;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.ibatis.type.Alias;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@Alias("UserFile")
public class UserFile {

    public static final int STATE_PASS = 1;//通过
    public static final int STATE_APPLY = 0;//申请
    private Long id;

    private int state;

    private String remark;

    private Date auditTime;

    private Date applyTime;

    private Logininfo auditor;

    private Logininfo applier;

    private int score;

    private String file;

    private SystemDictionaryItem fileType;

    public String getJsonString() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("username", this.getApplier().getUsername());
        map.put("image1", this.getFile());
        map.put("fileType", this.getFileType().getTitle());
        return JSON.toJSONString(map);
    }
}