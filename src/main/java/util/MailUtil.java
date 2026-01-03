package util;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;
import model.Contact;

public class MailUtil {

    private static final String FROM_EMAIL = "yourgmail@gmail.com";
    private static final String APP_PASSWORD = "xxxx xxxx xxxx xxxx";
    private static final String TO_EMAIL = "luonghoaisangvn@gmail.com";

    public static void sendContactMail(Contact c) {

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM_EMAIL, APP_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FROM_EMAIL));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(TO_EMAIL)
            );

            message.setSubject("Liên hệ mới từ khách hàng");

            String content = """
                    Họ tên: %s
                    Email: %s
                    SĐT: %s

                    Nội dung:
                    %s
                    """.formatted(
                    c.getFullName(),
                    c.getEmail(),
                    c.getPhone(),
                    c.getMessage()
            );

            message.setText(content);

            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
