package eon.p2p.base.service.impl;

import eon.p2p.base.domain.SystemDictionaryItem;
import eon.p2p.base.domain.UserFile;
import eon.p2p.base.domain.UserInfo;
import eon.p2p.base.mapper.UserFileMapper;
import eon.p2p.base.query.UserFileQueryObject;
import eon.p2p.base.query.page.PageResult;
import eon.p2p.base.service.IUserFileService;
import eon.p2p.base.service.IUserInfoService;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserFileService implements IUserFileService {

    @Autowired
    private UserFileMapper userfileMapper;

    @Autowired
    private IUserInfoService userService;

    @Override
    public List<UserFile> listUnSetTypeFiles(Long id, boolean unselected) {
        return this.userfileMapper.listUnSetTypeFiles(id, unselected);
    }

    @Override
    public void applyFile(String path) {
        UserFile uf = new UserFile();
        uf.setApplier(UserContext.getCurrent());
        uf.setApplyTime(new Date());
        uf.setFile(path);
        uf.setState(UserFile.STATE_APPLY);
        this.userfileMapper.insert(uf);
    }

    @Override
    public void applyTypes(Long[] ids, Long[] fileTypes) {
        for (int i = 0; i < ids.length; i++) {
            UserFile uf = this.userfileMapper.selectByPrimaryKey(ids[i]);
            SystemDictionaryItem type = new SystemDictionaryItem();
            type.setId(fileTypes[i]);
            uf.setFileType(type);
            this.userfileMapper.updateByPrimaryKey(uf);
        }
    }

    @Override
    public PageResult<UserFile> query(UserFileQueryObject qo) {
        Long count = userfileMapper.queryForCount(qo);
        //数据为0时
        if (count == 0) {
            return PageResult.EMPTY;
        }
        return new PageResult<>(userfileMapper.query(qo), count, qo.getCurrentPage(), qo.getPageSize());
    }

    @Override
    public List<UserFile> queryList(UserFileQueryObject qo) {
        return this.userfileMapper.query(qo);
    }

    @Override
    public void audit(Long id, String remark, int score, int state) {
        UserFile file = this.userfileMapper.selectByPrimaryKey(id);
        if (file != null && file.getState() == UserFile.STATE_APPLY) {
            file.setAuditor(UserContext.getCurrent());
            file.setAuditTime(new Date());
            file.setState(state);
            file.setRemark(remark);

            if (state == UserFile.STATE_PASS) {
                file.setScore(score);
                UserInfo user = this.userService.get(file.getApplier().getId());
                user.setScore(user.getScore() + score);
                this.userService.update(user);
            }
            this.userfileMapper.updateByPrimaryKey(file);
        }
    }
}
