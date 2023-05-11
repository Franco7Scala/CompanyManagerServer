package com.andreoidlnx.company_manager_server.services.stuffs;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import jakarta.annotation.Resource;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

@Service
public class MailManagementService {

    @Resource(name = "mail/alice")
    private Session mailSession;

    public void sendMessage(String email, String subject, String text) throws Exception {
        final Message message = new MimeMessage(mailSession);
        MimeMultipart multipart = new MimeMultipart();
        MimeBodyPart bodyPart = new MimeBodyPart();
        message.setSubject(subject);
        message.setRecipient(RecipientType.TO, new InternetAddress(email, ""));
        message.setFrom(new InternetAddress("francesco8ball@alice.it", "Company Manager"));
        bodyPart.setContent(text, "text/html");
        multipart.addBodyPart(bodyPart);
        message.setContent(multipart);
        new Thread() {
            @Override
            public void run() {
                try {
                    Transport.send(message);
                } catch ( MessagingException ex ) {
                    Logger.getLogger(MailManagementService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }.start();
    }
    
}
