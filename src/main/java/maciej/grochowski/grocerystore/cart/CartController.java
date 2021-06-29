package maciej.grochowski.grocerystore.cart;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.error.NotEnoughMoneyException;
import maciej.grochowski.grocerystore.product.Product;
import maciej.grochowski.grocerystore.user.User;
import maciej.grochowski.grocerystore.user.UserRepository;
import maciej.grochowski.grocerystore.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;
    private final UserRepository userRepository;

    @GetMapping()
    public String cartListPage(Model model) {
        model.addAttribute("cartProductList", cartService.getAllCartProducts());
        model.addAttribute("cartProductPrice", cartService.getCartProductsPrice());
        return "cart";
    }

    @GetMapping("/cartProductsPrice")
    public String getCartProductsPrice(ModelAndView model) {
        BigDecimal totalPrice = cartService.getCartProductsPrice();
        model.addObject("totalPrice", totalPrice);
        return "redirect:/cart";
    }

    @GetMapping("/addCartProduct/{id}")
    public String addCartProductForm(@PathVariable Integer id, Product product) {
        cartService.addCartProduct(product);
        return "redirect:/";
    }

    @GetMapping("/buyCartProduct")
    public String buyCartProduct(@ModelAttribute("user") User user, Model model, BindingResult result) throws NotEnoughMoneyException {
        if (result.hasErrors()) {
            var message = "";
            model.addAttribute("message", message);
        } else {
            if (cartService.getCartProductsPrice().compareTo(BigDecimal.ZERO) != 0) {
                cartService.buyCartProducts();
                var message = "Purchase complete.";
                model.addAttribute("message", message);
            }
        }
        return "cart";
    }

    @GetMapping("/deleteCartProduct/{id}")
    public String deleteCartProduct(@PathVariable Integer id, Product product) {
        cartService.deleteCartProduct(product);
        return "redirect:/cart";
    }
}
