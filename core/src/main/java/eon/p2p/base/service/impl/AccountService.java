package eon.p2p.base.service.impl;

import eon.p2p.base.domain.Account;
import eon.p2p.base.mapper.AccountMapper;
import eon.p2p.base.service.IAccountService;
import eon.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public void update(Account account) {
        int i = accountMapper.updateByPrimaryKey(account);
        //乐观锁,操作影响数返回为0,表示更新失败
        if (i == 0) {
            throw new RuntimeException("乐观锁失败,account:" + account.getId());
        }
    }

    @Override
    public void add(Account account) {
        this.accountMapper.insert(account);
    }

    @Override
    public Account get(Long id) {
        return this.accountMapper.selectByPrimaryKey(id);
    }

    @Override
    public Account getCurrent() {
        return this.get(UserContext.getCurrent().getId());
    }
}
