import eon.p2p.base.domain.RealAuth;
import eon.p2p.base.mapper.RealAuthMapper;
import eon.p2p.base.query.RealAuthQueryObject;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-website.xml")
public class Test {
    @Autowired
    private RealAuthMapper realAuthMapper;

    @org.junit.Test
    public void a() {
        List<RealAuth> query = realAuthMapper.query(new RealAuthQueryObject());
        System.err.println(query.size());
//        RealAuth one = realAuthMapper.test(19L);
//        System.err.println(one.getLogininfo());
    }
}
