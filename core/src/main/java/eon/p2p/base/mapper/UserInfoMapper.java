package eon.p2p.base.mapper;

import eon.p2p.base.domain.UserInfo;

import java.util.List;

public interface UserInfoMapper {
    Long deleteByPrimaryKey();

    void insert(UserInfo userInfo);

    int updateByPrimaryKey(UserInfo userInfo);

    UserInfo selectByPrimaryKey(Long id);

    List<UserInfo> selectAll();

    long judgePhoneIsBind(String phoneNubmer);
}