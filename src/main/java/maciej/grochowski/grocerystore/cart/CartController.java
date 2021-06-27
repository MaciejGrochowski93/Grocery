package maciej.grochowski.grocerystore.cart;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.exception.NotEnoughMoneyException;
import maciej.grochowski.grocerystore.product.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

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
        return "redirect:/cart";
    }

    @GetMapping("/addCartProduct/{id}")
    public String addCartProductForm(@PathVariable Integer id, Product product) {
        cartService.addCartProduct(product);
        return "redirect:/";
    }

    //    @PostMapping("/add")
//    public String addUser(@Valid User user, BindingResult result, Model model) {
//        String err = validationService.validateUser(user);
//        if (!err.isEmpty()) {
//            ObjectError error = new ObjectError("globalError", err);
//            result.addError(error);
//        }
//        if (result.hasErrors()) {
//            return "errors/addUser";
//        }
//        repository.save(user);
//        model.addAttribute("users", repository.findAll());
//        return "errors/home";
//    }

    @GetMapping("/buyCartProduct")
    public String buyCartProduct(BindingResult result) {
        if (result.hasErrors()) {
            try {
                cartService.buyCartProducts();
                cartService.deleteAllCartProducts();
            } catch (NotEnoughMoneyException e) {
                return "redirect:/cart";
            }
        }
        return "redirect:/cart";
    }

    @GetMapping("/deleteCartProduct/{id}")
    public String deleteCartProduct(@PathVariable Integer id, Product product) {
        cartService.deleteCartProduct(product);
        return "redirect:/cart";
    }
}
