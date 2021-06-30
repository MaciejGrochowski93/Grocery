package maciej.grochowski.grocerystore;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.cart.CartService;
import maciej.grochowski.grocerystore.product.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
public class ViewController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("productList", productService.getAllProducts());
        model.addAttribute("cartProductAmount", cartService.getCartProductsAmount());
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

    @GetMapping("/default")
    public String defaultSite(HttpServletRequest request) {
        if (request.isUserInRole("ROLE_ADMIN")) {
            return "redirect:/admin";
        } else return "redirect:/";
    }
}
