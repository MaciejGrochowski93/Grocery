package maciej.grochowski.grocerystore;

import maciej.grochowski.grocerystore.product.Product;
import maciej.grochowski.grocerystore.product.ProductService;
import maciej.grochowski.grocerystore.user.User;
import maciej.grochowski.grocerystore.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

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

    @PostMapping("/admin")
    public String addNewProduct(@ModelAttribute("product") Product product) {
        productService.addProduct(product);
        return "redirect:/admin_page";
    }

    @PostMapping("/register")
    public String register(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "add_user";
    }

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
