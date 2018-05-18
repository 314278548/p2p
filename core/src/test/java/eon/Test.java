package eon;

import eon.p2p.base.domain.UserInfo;
import eon.p2p.base.mapper.UserInfoMapper;
import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.service.impl.UserInfoService;
import eon.p2p.base.util.BitStatesUtils;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class Test {
    @Autowired
    private IUserInfoService userInfoService;

    @org.junit.Test
    public void index() {
        UserInfo userInfo = userInfoService.get(38l);
        userInfo.setBitState(BitStatesUtils.removeState(userInfo.getBitState(), BitStatesUtils.OP_REAL_AUTH));
    }
}
