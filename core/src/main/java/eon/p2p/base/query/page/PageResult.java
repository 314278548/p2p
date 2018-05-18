package eon.p2p.base.query.page;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 封装高级查询结果
 */
@Setter
@Getter
@ToString
public class PageResult<T> {

    private List<T> data = new ArrayList<>();
    private Long totalCount;
    private Long currentPage;
    private Long pageSize;
    private Long nextPage;
    private Long prevPage;
    private Long totalPage;

    /**
     * 空的PageResult
     */
    public static final PageResult EMPTY = new PageResult(Collections.emptyList(), 0l, 1l, 0l);

    public PageResult(List<T> data, Long totalCount, Long currentPage, Long pageSize) {
        this.data = data;
        this.totalCount = totalCount;
        this.pageSize = pageSize;
        this.currentPage = currentPage;

        this.totalPage = this.pageSize == 0 ? 1 : this.totalCount % this.pageSize == 0 ? this.totalCount
                / this.pageSize : this.totalCount / this.pageSize + 1;
        this.prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
        this.nextPage = currentPage + 1 < totalPage ? currentPage + 1 : totalPage;
    }

}
