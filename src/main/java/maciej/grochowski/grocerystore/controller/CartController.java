package maciej.grochowski.grocerystore.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.Product;
import maciej.grochowski.grocerystore.model.User;
import maciej.grochowski.grocerystore.security.MyUserDetails;
import maciej.grochowski.grocerystore.service.CartService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

    @ApiOperation(value = "Shows the Products you added to your cart.")
    @GetMapping()
    public String cartListPage(Model model) {
        model.addAttribute("cartProductSet", cartService.getAllCartProducts());
        model.addAttribute("cartProductPrice", cartService.getCartProductsPrice());
        return "cart";
    }

    @ApiOperation(value = "Shows the total price of products in the cart.")
    @GetMapping("/cartProductsPrice")
    public String getCartProductsPrice(ModelAndView model) {
        BigDecimal totalPrice = cartService.getCartProductsPrice();
        model.addObject("totalPrice", totalPrice);
        return "redirect:/cart";
    }

    @ApiOperation(value = "Adds the Product to your cart.")
    @GetMapping("/addCartProduct/{id}")
    public String addCartProductForm(@PathVariable Integer id, Product product, HttpServletRequest request) {
        cartService.addCartProduct(product);
        return getPreviousPageByRequest(request).orElse("/");
    }

    @ApiOperation(value = "Allows - USER - to buy the product, and pay for it.")
    @GetMapping("/buyCartProduct")
    public String buyCartProduct(@ModelAttribute("user") User user, @AuthenticationPrincipal MyUserDetails userDetails,
                                 Model model) {
        BigDecimal cartProductsPrice = cartService.getCartProductsPrice();
        BigDecimal userDetailsMoney = userDetails.getMoney();
        var afterBuyMessage = "Your cart is empty.";

        if (cartProductsPrice != BigDecimal.ZERO) {
            if (userDetailsMoney.compareTo(cartProductsPrice) >= 0) {
                cartService.buyCartProducts(userDetails);
                afterBuyMessage = "Purchase complete.";
            } else if (userDetailsMoney.compareTo(cartProductsPrice) < 0) {
                afterBuyMessage = "Not enough money to complete the purchase.";
            }
        }

        model.addAttribute("afterBuyMessage", afterBuyMessage);
        return "cart";
    }

    @ApiOperation(value = "This method increases the quantity of the particular Product you can possibly buy.")
    @GetMapping("/oneMoreProduct/{id}")
    public String oneMoreSameProduct(@PathVariable Integer id, Product product) {
        cartService.oneMoreSameProduct(product);
        return "redirect:/cart";
    }

    @ApiOperation(value = "This method decreases the quantity of the particular Product you can possibly buy.")
    @GetMapping("/oneLessProduct/{id}")
    public String oneLessSameProduct(@PathVariable Integer id, Product product) {
        cartService.oneLessSameProduct(product);
        return "redirect:/cart";
    }

    @ApiOperation(value = "This method removes the Product from your cart.")
    @GetMapping("/deleteCartProduct/{id}")
    public String deleteCartProduct(@PathVariable Integer id, Product product) {
        cartService.deleteCartProduct(product);
        return "redirect:/cart";
    }
}
