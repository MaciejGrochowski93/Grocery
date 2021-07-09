package maciej.grochowski.grocerystore.controller;

import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "The homepage, contains all Products.")
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

    @ApiOperation(value = "This page appears after successful login.")
    @GetMapping("/hello")
    public String userPage() {
        return "hello_page";
    }
}
