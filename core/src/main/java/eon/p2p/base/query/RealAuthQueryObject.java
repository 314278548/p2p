package eon.p2p.base.query;

import eon.p2p.base.util.DateUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 实名认证高级查询对象
 */
@Getter
@Setter@ToString
public class RealAuthQueryObject extends QueryObject {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    private int state = -1;

    private Long applierId;

    public Date getBeginTime() {
        return DateUtil.startDate(this.beginTime);
    }

    public Date getEndTime() {
        return DateUtil.endDate(this.endTime);
    }

}
