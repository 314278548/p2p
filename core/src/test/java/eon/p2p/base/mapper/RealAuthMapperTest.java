package eon.p2p.base.mapper;

import eon.p2p.base.query.RealAuthQueryObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class RealAuthMapperTest {

    @Autowired
    private RealAuthMapper realAuthMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @Test
    public void selectAll() {
        Long count = realAuthMapper.count(new RealAuthQueryObject());
        System.err.println(count);
//        RealAuth one = realAuthMapper.getOne(1L);
//        System.err.println(one.getLogininfo());
    }
}