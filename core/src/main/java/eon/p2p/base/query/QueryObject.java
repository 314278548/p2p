package eon.p2p.base.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 高级查询基类
 * <p>
 * 当前页为1
 * <p>
 * 默认页面信息大小为5
 */
@Getter
@Setter
@ToString
public class QueryObject {
    protected Long currentPage = 1l;
    protected Long pageSize = 5l;


    public Long getStart() {
        return (this.currentPage - 1) *
                this.pageSize;
    }

}
