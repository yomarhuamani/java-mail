package com.tesla.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendZohoMailTLS {
    public static void main(String[] args) {
        // Recipient's email ID needs to be mentioned.
        String to = "sendToAddress@example.com";//change accordingly

        // Sender's email ID needs to be mentioned
        String from = "sendFromAddress@zoho.com";//change accordingly

        final String username = "sendFromAddress@zoho.com";//change accordingly
        final String password = "*******";//change accordingly

        // Zoho's SMTP server
        String host = "smtp.zoho.com";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", host);

        // Get the Session object.
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            System.out.println("Indisde rty");
            // Create a default MimeMessage object.
            Message message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));

            // Set Subject: header field
            message.setSubject("Zoho - Email Test");

            // Now set the actual message
            message.setText("Hello, this is sample email to check/send "
                    + "email using JavaMailAPI from Zoho");

            // Send message
            Transport.send(message);

            System.out.println("Sent message successfully.... from Zoho");

        } catch (MessagingException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}