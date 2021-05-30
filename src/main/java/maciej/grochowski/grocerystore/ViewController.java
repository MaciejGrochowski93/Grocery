package maciej.grochowski.grocerystore;

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
public class ViewController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "index";
    }

    @GetMapping("/user")
    public String buyProductForm(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "user_page";
    }

    @GetMapping("user/buyProductForm")
    public String buyProduct(String email, Product product) {
        userService.buyProduct(email, product);
        return "redirect:/user";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        return "admin_page";
    }

    @GetMapping("/addProduct")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        return "new_product";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/register")
    public String addRequestForm(Model model) {
        model.addAttribute("registrationRequest", new RegistrationRequest());
        return "register_page";
    }

    @PostMapping("/register")
    public String registerRequest(@ModelAttribute("registrationRequest") RegistrationRequest registrationRequest) {
        try {
            registrationService.register(registrationRequest);
        }
        catch (IllegalStateException e) {
            System.err.println(e.getMessage());
        }
        return "register_page";
    }

    @GetMapping("/confirm")
    public String confirmToken(@RequestParam("token") String token){
        registrationService.confirmToken(token);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
