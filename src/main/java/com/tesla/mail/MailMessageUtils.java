package com.tesla.mail;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.util.List;

public class MailMessageUtils {

    public static Multipart messageMultipart(List<File> listOfFlies) throws MessagingException {

        BodyPart messageBody = new MimeBodyPart();
        messageBody.setText("Hello, this is sample email with attachments to check/send "
                + "email using JavaMailAPI from " + ConfigConsts.SMPT_HOST_NAME);

        Multipart multipart = new MimeMultipart();

        multipart.addBodyPart(messageBody);

        addFileToMultipart(listOfFlies, multipart);

        return multipart;
    }

    private static void addFileToMultipart(List<File> listOfFlies, Multipart multipart) {

        listOfFlies.stream()
                .filter(file -> file.isFile())
                .forEach(f -> {
                    try {
                        multipart.addBodyPart(addAttachment(f));
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                });
    }

    private static MimeBodyPart addAttachment(File file) throws MessagingException {

        MimeBodyPart attachmentBodyPart = new MimeBodyPart();
        DataSource source = new FileDataSource(file);
        attachmentBodyPart.setDataHandler(new DataHandler(source));
        attachmentBodyPart.setFileName(file.getName());

        return attachmentBodyPart;
    }
}