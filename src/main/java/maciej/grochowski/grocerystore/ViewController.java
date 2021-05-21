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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String addUserForm(Model model) {
        model.addAttribute("user", new User());
        return "register_page";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute("user") User user) {
        userService.signUpUser(user);
        return "redirect:/user";
    }

//    @GetMapping("/register")
//    public String addRequestForm(Model model) {
//        model.addAttribute("request", new RegistrationRequest());
//        return "register_page";
//    }
//
//    @PostMapping("/register")
//    public String registerRequest(@ModelAttribute("request") RegistrationRequest request) {
//        registrationService.register(request);
//        return "redirect:/user";
//    }

    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) {
        userService.signUpUser(user);
        return "redirect:/index";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }
}
