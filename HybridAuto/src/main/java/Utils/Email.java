package Utils;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.Random;

public class Email {
    public static String SendCode(String email) {
        final String username = "atariq344@gmail.com";
        final String password = "volbaqgqaeaxxufp";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com");
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); //TLS

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        String code = null;
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("atariq344@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email)
            );
            message.setSubject("Verification Code - Hybrid AutoTech");
            Random random = new Random();
            code = String.valueOf(random.nextInt(000000,999999));
            message.setText(code);

            Transport.send(message);
            new Notification("Code Send");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return code;
    }
}
