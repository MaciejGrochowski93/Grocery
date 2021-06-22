package maciej.grochowski.grocerystore.cart;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.product.Product;
import maciej.grochowski.grocerystore.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final UserService userService;

    @GetMapping()
    public String cartListPage(Model model) {
        model.addAttribute("cartProductList", cartService.getAllCartProducts());
        model.addAttribute("cartProductPrice", cartService.getCartProductsPrice());
        return "cart";
    }

    @GetMapping("/cartProductsPrice")
    public String getCartProductsPrice(ModelAndView model) {
        double totalPrice = cartService.getCartProductsPrice();
        model.addObject("totalPrice", totalPrice);
        return "redirect:/admin";
    }

//            @PostMapping("/cartProductsPrice")
//            public String getCartProductsPrice(@ModelAttribute Double price) {
//                cartService.getCartProductPrice();
//                return "redirect:/admin";
//            }

//    @GetMapping("/buyCartProduct")
//    public String buyCartProduct(Product product) {
//        double price = cartService.buyCartProduct(product);
//        userService.buyProduct(price);
//        return "redirect:/";
//    }

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
