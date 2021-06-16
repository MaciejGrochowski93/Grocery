package maciej.grochowski.grocerystore.cart;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping()
    public String cartListPage(Model model) {
        model.addAttribute("cartProductList", cartService.getAllCartProducts());
        return "cart";
    }

    @GetMapping("/buyCartProduct/{id}")
    public String buyCartProduct(@PathVariable Integer id, Product product) {
        cartService.buyCartProduct(product);
        return "redirect:/";
    }

    @GetMapping("/addCartProduct/{id}")
    public String addCartProductForm(@PathVariable Integer id, Product product) {
        cartService.addCartProduct(product);
        return "redirect:/";
    }

    @GetMapping("/deleteCartProduct/{id}")
    public String deleteCartProduct(@PathVariable Integer id, Product product) {
        cartService.deleteCartProduct(product);
        return "redirect:/cart";
    }
}
