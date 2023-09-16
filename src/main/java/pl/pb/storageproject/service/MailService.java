package pl.pb.storageproject.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Configuration
public class MailService {

    private final Logger logger = LogManager.getLogger(getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendMail(String to, String text, String subject, boolean isHtmlContent) throws MessagingException{
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper message = new MimeMessageHelper(mimeMessage,true);

        message.setTo(to);
        message.setText(text,isHtmlContent);
        message.setSubject(subject);
        javaMailSender.send(mimeMessage);
    }
}
