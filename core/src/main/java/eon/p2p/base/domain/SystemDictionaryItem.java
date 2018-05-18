package eon.p2p.base.domain;

import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * 数据字典明细
 */
@Getter
@Setter
public class SystemDictionaryItem extends BaseDomain {

    private Long parentId; //数据字典明细对应的分类id
    //    private String intro;
    private String title;
    //    private String value; //数据字典明细可选值
    private Long sequence; //数据字典明细在该分类中的排序


    public String jsonString() {
        Map<String, Object> map = new HashMap<>();
        map.put("id", this.getId());
        map.put("title", this.getTitle());
        map.put("sequence", this.getSequence());
        return JSON.toJSONString(map);
    }
}
