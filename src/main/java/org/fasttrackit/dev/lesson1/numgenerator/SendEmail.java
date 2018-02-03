package org.fasttrackit.dev.lesson1.numgenerator;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class SendEmail implements Runnable{

    private String toEmail;

    public SendEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public void sendEmailUsingGmail () {
        Thread t = new Thread(this);
        t.start();
    }

    public void run(){


        final String username = "fasttrackit.intro@gmail.com "; // change this to reflect your own account
        final String password = "fasttrackit.intro1";  // change this to reflect your own account

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Num-guess");
            message.setText("Congratulation!");
            Transport.send(message);

        } catch (Exception e) {

        }
    }
}
