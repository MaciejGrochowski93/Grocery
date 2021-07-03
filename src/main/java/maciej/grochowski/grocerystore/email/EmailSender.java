package maciej.grochowski.grocerystore.email;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class EmailSender {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailSender.class);
    private final JavaMailSender javaMailSender;

    @Async
    public void sendMail(String toWho, String body) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
            messageHelper.setTo(toWho);
            messageHelper.setSubject("Confirm your email");
            messageHelper.setText(body, true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("Failed to send email", e);
            throw new IllegalStateException("Failed to send email");
        }

    }
}
