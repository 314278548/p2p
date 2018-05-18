package eon.p2p.mgr.listener;

import eon.p2p.base.service.ILogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * 当spring启动的时候
 * 初始化第一个管理员
 */
@Component
public class InitAdimListner implements ApplicationListener<ContextRefreshedEvent> {//容器启动事件

    @Autowired
    private ILogininfoService logininfoServicel;

    /**
     * 初始化第一个管理员
     *
     * @param contextRefreshedEvent
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        logininfoServicel.initAdmin();
    }

}
