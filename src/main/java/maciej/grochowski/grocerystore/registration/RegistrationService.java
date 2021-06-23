package maciej.grochowski.grocerystore.registration;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.registration.email.EmailSender;
import maciej.grochowski.grocerystore.registration.token.ConfirmationToken;
import maciej.grochowski.grocerystore.registration.token.ConfirmationTokenService;
import maciej.grochowski.grocerystore.user.User;
import maciej.grochowski.grocerystore.user.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class RegistrationService {

    private final UserService userService;
    private final EmailValidator emailValidator;
    private final ConfirmationTokenService confirmationTokenService;
    private final EmailSender emailSender;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException("Invalid email");
        }

        User user = userService.signUpUser(
                new User(request.getEmail(), request.getPassword(), request.getFirstName())
        );

        String token = userService.generateToken(user);

        String link = "http://localhost:8080/confirm?token=" + token;

        emailSender.sendMail(request.getEmail(), buildEmail(request.getFirstName(), link));
        return token;
    }

    @Transactional
    public void confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService
                .getToken(token)
                .orElseThrow(() -> new IllegalStateException("Token was not found."));

        if (confirmationToken.getConfirmationTime() != null) {
            throw new IllegalStateException("Email is already confirmed.");
        }

        if (confirmationToken.getExpirationTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Confirmation Token has expired.");
        }

        confirmationTokenService.confirmToken(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
    }

    private String buildEmail(String name, String link) {
        return "Hello, " + name + ". "
                + "<p>Thank you for registering at Maciej's Grocery.</p>"
                + "<p>Please, confirm your registration, and activate your profile by clicking on the link: </p>"
                + "<p><a href=" + link + "> Confirmation</a></p>"
                + "<p>If you didn't register at Maciej's Grocery, please ignore this email.</p>"
                + "<br><br>"
                + "Maciej Grochowski";
    }
}
