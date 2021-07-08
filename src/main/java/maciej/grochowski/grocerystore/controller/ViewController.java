package maciej.grochowski.grocerystore.controller;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.Product;
import maciej.grochowski.grocerystore.service.CartService;
import maciej.grochowski.grocerystore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
}
