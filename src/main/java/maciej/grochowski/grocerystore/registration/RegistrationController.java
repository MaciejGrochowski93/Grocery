package maciej.grochowski.grocerystore.registration;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
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
    public String registerRequest(@ModelAttribute("registrationRequest")
                                  @Valid RegistrationRequest registrationRequest,
                                  BindingResult result) {
        if (result.hasErrors()) {
            return "register_page";
        }
        try {
            registrationService.register(registrationRequest);
        } catch (IllegalStateException e) {
            LOGGER.error(e.getMessage());
        }
        return "index";
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return "redirect:/";
    }
}
