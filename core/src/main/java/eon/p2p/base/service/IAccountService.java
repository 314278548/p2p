package eon.p2p.base.service;

import eon.p2p.base.domain.Account;

/**
 * 账户相关服务
 */
public interface IAccountService {

    /**
     * 更新操作,乐观锁
     *
     * @param account
     */
    void update(Account account);

    /**
     * 添加账户信息
     *
     * @param account
     */
    void add(Account account);

    /**
     * 获取账户信息
     *
     * @param id
     * @return
     */
    Account get(Long id);

    /**
     * 获取当前账户信息
     *
     * @return
     */
    Account getCurrent();
}

