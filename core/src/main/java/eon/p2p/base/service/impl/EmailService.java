package eon.p2p.base.service.impl;

import eon.p2p.base.service.IEmailService;
import org.springframework.stereotype.Service;

@Service
public class EmailService implements IEmailService {

    @Override
    public void sendEmail(String email) {
        System.err.println("发送邮件");
    }
}
