package eon.p2p.base.query;

import eon.p2p.base.util.StringUtil;

public class SystemDictionaryQueryObject extends QueryObject {
    private String keyword;
    private Boolean state;

    //字典明细父字典的id
    private Long parentId;

    public String getKeyword() {
        return StringUtil.hasLength(keyword) ? keyword : null;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
