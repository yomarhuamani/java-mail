package com.tesla.mail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendNTLM {
    public static void main(String[] args) {
        String to = "yomar.huamani@teslatec.pe";

        String from = "riesgooperacional@baustro.fin.ec";

        final String username = "riesgooperacional";
        final String password = "********";

        String host = "mail.austro.grp.fin";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", ConfigConsts.SMPT_HOST_ADDRESS);
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth.ntlm.domain", "baustro.grp.fin");
        props.put("mail.smtp.auth.mechanisms","NTLM");
        props.put("mail.debug", "true");

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

            message.setSubject("NTLM - Email Test");

            message.setText("Hello, this is sample email to check/send email using JavaMailAPI from GMAIL");
            Transport.send(message);

            System.out.println("Sent message successfully.... from NTLM");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
