package eon.p2p.base.controller;

import eon.p2p.base.service.IBankInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 * 前段银行相关
 */
@Controller
public class BankInfoController {
    @Autowired
    private IBankInfoService bankInfoService;
}
