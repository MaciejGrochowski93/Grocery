package maciej.grochowski.grocerystore.controller;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.Product;
import maciej.grochowski.grocerystore.service.CartService;
import maciej.grochowski.grocerystore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@AllArgsConstructor
public class ViewController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/")
    public String index(Model model) {
        List<Product> allProducts = productService.getAllProducts();
        model.addAttribute("productList", allProducts);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/hello")
    public String userPage() {
        return "hello_page";
    }

//    @GetMapping("/default")
//    public String defaultSite(HttpServletRequest request) {
//        if (request.isUserInRole("ROLE_ADMIN")) {
//            return "redirect:/admin";
//        } else return "redirect:/";
//    }
}
