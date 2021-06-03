package maciej.grochowski.grocerystore;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.product.Product;
import maciej.grochowski.grocerystore.product.ProductService;
import maciej.grochowski.grocerystore.registration.RegistrationRequest;
import maciej.grochowski.grocerystore.registration.RegistrationService;
import maciej.grochowski.grocerystore.user.User;
import maciej.grochowski.grocerystore.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ViewController {

    private final ProductService productService;
    private final UserService userService;
    private final RegistrationService registrationService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "admin_page";
    }
}
