package com.finalProject.BDS.controller;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

@RestController
public class EmailController {
    @GetMapping("/gui-email")
    public String sendEmail() throws AddressException, MessagingException, IOException {
        sendmail();
        return "Email sent successfully!";
    }

    private void sendmail() throws AddressException, MessagingException, IOException{
        Properties prop = new Properties();
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true");
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication("chivuivet@gmail.com","lvhv tawk nhdf idmb");
            }
        });
        Message msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress("giahaoqgh2004@gmail.com", false));
        msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse("giahaoqgh2004@gmail.com"));
        msg.setSubject("Yêu cầu xem trước");
        msg.setContent("Yêu cầu xem trước","text/html");
        msg.setSentDate(new Date());

        MimeBodyPart msgBodyPart = new MimeBodyPart();
        msgBodyPart.setContent("yolo","text/html");

        Multipart mtp = new MimeMultipart();
        mtp.addBodyPart(msgBodyPart);
        MimeBodyPart attachPart = new MimeBodyPart();
        attachPart.attachFile("src/main/resources/static/images/about.jpg");
        mtp.addBodyPart(attachPart);
        msg.setContent(mtp);
        Transport.send(msg);
    }
}