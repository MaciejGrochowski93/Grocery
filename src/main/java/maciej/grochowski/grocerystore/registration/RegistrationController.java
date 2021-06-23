package maciej.grochowski.grocerystore.registration;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationController.class);

    @GetMapping("/register")
    public String addRequestForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register_page";
    }

    @PostMapping("/register")
    public String registerRequest(@ModelAttribute("registrationRequest") RegistrationRequest registrationRequest) {
        try {
            registrationService.register(registrationRequest);
        } catch (IllegalStateException e) {
            LOGGER.error(e.getMessage());
        }
        return "register_page";
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return "redirect:/";
    }
}
