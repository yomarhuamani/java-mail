package com.tesla.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendGMailTLS {
    public static void main(String[] args) {
        String to = "sendToAddress@example.com";

        String from = "sendFromAddress@gmail.com";

        final String username = "sendFromAddress@gmail.com";
        final String password = "********";

        String host = "smtp.gmail.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", host);

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);

            message.setFrom(new InternetAddress(from));

            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            message.setSubject("Gmail - Email Test");

            message.setText("Hello, this is sample email to check/send "
                    + "email using JavaMailAPI from GMAIL");

            Transport.send(message);

            System.out.println("Sent message successfully.... from GMAIL");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}