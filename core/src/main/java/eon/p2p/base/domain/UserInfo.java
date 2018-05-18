package eon.p2p.base.domain;

import eon.p2p.base.util.BitStatesUtils;
import eon.p2p.base.util.StringUtil;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户相关信息
 */
@Getter
@Setter
public class UserInfo extends BaseDomain {

    //基础分控分数
    public static final int BASE_SCORE = 30;

    private int version;
    private long bitState;//状态码
    private String realName;
    private String idNumber;
    private String phoneNumber;
    private String email;
    private int score;

    private Long realAuthId;//实名认证对象id

    private SystemDictionaryItem incomeGrade;//收入
    private SystemDictionaryItem marriage;//婚配
    private SystemDictionaryItem kidCount;//子女
    private SystemDictionaryItem educationBackground;//教育背景
    private SystemDictionaryItem houseCondition;//住房条件


    /**
     * 是否绑定手机
     *
     * @return
     */
    public boolean isBindPhone() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_PHONE) && StringUtil.hasLength(this.phoneNumber) ? true : false;
    }

    /**
     * 获取风控基本分
     *
     * @return
     */
    public int getBaseScore() {
        return UserInfo.BASE_SCORE;
    }

    /**
     * 是否绑定邮箱
     *
     * @return
     */
    public boolean isBindEmail() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BIND_EMAIL) && StringUtil.hasLength(this.email) ? true : false;
    }


    /**
     * 是否完善基本信息
     *
     * @return
     */
    public boolean isBindInfo() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_BASIC_INFO);
    }

    /**
     * 是否完成身份认证
     *
     * @return
     */
    public boolean isBindAuth() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_REAL_AUTH);
    }

    /**
     * 是否完成视频认证
     *
     * @return
     */
    public boolean isBindVedioAuth() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_VEDIO_AUTH);
    }

    /**
     * 是否达到基本风控分数
     *
     * @return
     */
    public boolean isGreaterThanBaseScore() {
        return this.score >= this.BASE_SCORE;
    }

    /**
     * 是否含有借款进程
     * @return
     */
    public boolean hasBidRequestProcess() {
        return BitStatesUtils.hasState(this.bitState, BitStatesUtils.OP_HAS_BIDRQUEST);
    }

    public void addState(long state) {
        this.setBitState(BitStatesUtils.addState(this.bitState, state));
    }
    public void removeState(long state) {
        this.setBitState(BitStatesUtils.removeState(this.bitState, state));
    }


}
