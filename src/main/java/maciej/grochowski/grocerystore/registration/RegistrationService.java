package maciej.grochowski.grocerystore.registration;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.registration.email.EmailSender;
import maciej.grochowski.grocerystore.registration.token.ConfirmationToken;
import maciej.grochowski.grocerystore.registration.token.ConfirmationTokenService;
import maciej.grochowski.grocerystore.user.User;
import maciej.grochowski.grocerystore.user.UserService;
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

        String token = userService.signUpUser(
                new User(request.getEmail(), request.getPassword(), request.getFirstName())
        );

        String message = "Hello, " + request.getFirstName() +
                ". Please, confirm your email if you want to register at Maciej's Grocery.";
        emailSender.sendMail(request.getEmail(), message);
        return token;
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken =
                confirmationTokenService.getToken(token).orElseThrow(() -> new IllegalStateException("Token was not found."));

        if (confirmationToken.getConfirmationTime() != null) {
            throw new IllegalStateException("Email is already confirmed.");
        }

        else if (confirmationToken.getConfirmationTime().isBefore(LocalDateTime.now())) {
            throw new IllegalStateException("Confirmation Token has expired.");
        }

        confirmationTokenService.confirmToken(token);
        userService.enableUser(confirmationToken.getUser().getEmail());
        return "Token confirmed.";
    }
}
