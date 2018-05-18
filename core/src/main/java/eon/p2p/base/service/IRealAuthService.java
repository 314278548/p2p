package eon.p2p.base.service;

import eon.p2p.base.domain.Logininfo;
import eon.p2p.base.domain.RealAuth;
import eon.p2p.base.query.RealAuthQueryObject;
import eon.p2p.base.query.page.PageResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 账户实名相关
 */
public interface IRealAuthService {

    RealAuth get(Long id);

    String upload(MultipartFile file);

    void apply(RealAuth realAuth);

    PageResult<RealAuth> query(RealAuthQueryObject qo);

    void update(RealAuth r);

    void audit(Long id, int state, String remark);

    List<Logininfo> listByKeyword(String keyword);
}
