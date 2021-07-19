import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Properties;


public class SendEmail {

    public static void main(String[] args) throws GeneralSecurityException {

        final String username = "smtp177@totthoapa.gov.bd";
        final String password = "SmTp%6189";

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "103.48.18.177");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
        // prop.put("mail.smtp.starttls.enable", "true"); //TLS

        prop.put("mail.smtp.socketFactory.port", "25"); //SSL Port
        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        prop.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
//        prop.put("mail.smtp.port", "465"); //SMTP Port

//        MailSSLSocketFactory socketFactory= new MailSSLSocketFactory();
//        socketFactory.setTrustAllHosts(true);
//        prop.put("mail.pop3s.ssl.socketFactory", socketFactory);

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hmtmcse@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("hmtmcse@gmail.com")
            );
            message.setSubject("Testing Gmail TLS");
            message.setText("Dear Mail Crawler,"
                    + "\n\n Please do not spam my email!");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
