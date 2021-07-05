package maciej.grochowski.grocerystore.controller;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.error.NotEnoughMoneyException;
import maciej.grochowski.grocerystore.model.Product;
import maciej.grochowski.grocerystore.model.User;
import maciej.grochowski.grocerystore.security.MyUserDetails;
import maciej.grochowski.grocerystore.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    private Optional<String> getPreviousPageByRequest(HttpServletRequest request) {
        return Optional.ofNullable(request.getHeader("Referer")).map(requestUrl -> "redirect:" + requestUrl);
    }

    @GetMapping()
    public String cartListPage(Model model) {
        model.addAttribute("cartProductSet", cartService.getAllCartProducts());
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
    public String addCartProductForm(@PathVariable Integer id, Product product, HttpServletRequest request) {
        cartService.addCartProduct(product);
        return getPreviousPageByRequest(request).orElse("/");
    }

    @GetMapping("/buyCartProduct")
    public String buyCartProduct(@ModelAttribute("user") User user, @AuthenticationPrincipal MyUserDetails userDetails,
                                 Model model, BindingResult result) throws NotEnoughMoneyException {
        String afterBuyMessage = "";
        if (!result.hasErrors() && cartService.getCartProductsPrice().compareTo(BigDecimal.ZERO) != 0) {
            cartService.buyCartProducts(userDetails);
            afterBuyMessage = "Purchase complete.";
        }

        model.addAttribute("afterBuyMessage", afterBuyMessage);
        return "cart";
    }

    @GetMapping("/oneMoreProduct/{id}")
    public String oneMoreSameProduct(@PathVariable Integer id, Product product) {
        cartService.oneMoreSameProduct(product);
        return "redirect:/cart";
    }

    @GetMapping("/oneLessProduct/{id}")
    public String oneLessSameProduct(@PathVariable Integer id, Product product) {
        cartService.oneLessSameProduct(product);
        return "redirect:/cart";
    }

    @GetMapping("/deleteCartProduct/{id}")
    public String deleteCartProduct(@PathVariable Integer id, Product product) {
        cartService.deleteCartProduct(product);
        return "redirect:/cart";
    }
}
