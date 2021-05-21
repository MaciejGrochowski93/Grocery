package maciej.grochowski.grocerystore.registration.email;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class EmailSender {

    private JavaMailSender javaMailSender;

    @Async
    public void sendMail(String toWho, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("maciej@gmail.com");
            messageHelper.setTo(toWho);
            messageHelper.setSubject("Confirm your email");
            messageHelper.setText(body);
        } catch (MessagingException e) {
            throw new IllegalStateException("Failed to send email");
        }

    }
}
