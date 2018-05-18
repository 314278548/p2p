package eon.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
@Getter@Setter
public class Bid {
    private Long id;

    private BigDecimal actualRate;//利率

    private BigDecimal availableAmount;//投资金额

    private Long bidRequestId;//关联标的id

    private Logininfo bidUser;//投标人

    private Date bidTime;//投标时间

    private String bidRequestTitle;////标名

}