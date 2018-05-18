package eon.p2p.base.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据字典分类
 */
@Getter
@Setter
@ToString
public class SystemDictionary extends BaseDomain {

    public static final boolean STATE_NORMAL = true;//正常
    public static final boolean STATE_ABNORMAL = false;//废弃

    private String sn;
    private String title;
    private String intro;
    private boolean state = STATE_NORMAL;//初始正常

    public String jsonString() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("sn", this.getSn());
        map.put("title", this.getTitle());
        map.put("intro", this.getIntro());
        return JSON.toJSONString(map);
    }

}
