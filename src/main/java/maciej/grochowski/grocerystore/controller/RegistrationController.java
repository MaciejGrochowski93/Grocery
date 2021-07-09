package maciej.grochowski.grocerystore.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.RegistrationRequest;
import maciej.grochowski.grocerystore.service.RegistrationService;
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

    @ApiOperation(value = "Allows you to register a new account.",
        notes = "New Users have automatic USER_ROLE")
    @GetMapping("/register")
    public String addRequestForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register_page";
    }

    @ApiOperation(value = "Allows you to register a new account.",
            notes = "Email must consist of 6-30 letters, password: 6-30, name: 1-30." +
                    "In order to unlock the account, you have to click on the confirmation link on your email.")
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
        return "redirect:/";
    }

    @ApiOperation(value = "Decides whether your account will be enabled.",
            notes = "You have 20 min to click the confirmation link in your email, afterwards the confirmation token will expire.")
    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token) {
        registrationService.confirmToken(token);
        return "redirect:/";
    }
}
