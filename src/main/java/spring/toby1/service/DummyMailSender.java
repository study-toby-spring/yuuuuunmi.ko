package spring.toby1.service;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by yuuuunmi on 2017. 11. 1..
 */
public class DummyMailSender implements MailSender{
    public void send(SimpleMailMessage simpleMailMessage) throws MailException {

    }

    public void send(SimpleMailMessage... simpleMailMessages) throws MailException {

    }
}
