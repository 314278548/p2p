package eon.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter@Setter
public class VedioAuth {
    private Long id;

    private int state;

    private String remark;

    private Date auditTime;

    private Date applyTime;

    private Logininfo auditor;

    private Logininfo applier;

}