package maciej.grochowski.grocerystore.registration;

import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

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
            System.err.println(e.getMessage());
        }
        return "register_page";
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return "redirect:/";
    }
}
