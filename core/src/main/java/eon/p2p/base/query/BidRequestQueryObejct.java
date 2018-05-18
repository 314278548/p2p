package eon.p2p.base.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter@ToString
public class BidRequestQueryObejct extends QueryObject {
    private Integer state;
    private Long createUserId;

    private int[] states;//状态
    private String orderBy;//排序类型
    private String orderType;//升序降序

    private Integer noState;//不含有的状态
}
