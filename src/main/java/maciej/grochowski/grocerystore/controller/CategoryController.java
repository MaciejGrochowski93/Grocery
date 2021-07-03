package maciej.grochowski.grocerystore.controller;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.service.CartService;
import maciej.grochowski.grocerystore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/candies")
    public String getCandiesList(Model model) {
        model.addAttribute("candiesList", productService.getProdByCategory("Candies"));
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "candies";
    }

    @GetMapping("/chemicals")
    public String getChemicalsList(Model model) {
        model.addAttribute("chemicalsList", productService.getProdByCategory("Chemicals"));
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "chemicals";
    }

    @GetMapping("/breadstuff")
    public String getBreadstuffList(Model model) {
        model.addAttribute("breadstuffList", productService.getProdByCategory("Breadstuff"));
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "breadstuff";
    }

    @GetMapping("/vegetables")
    public String getVegetablesList(Model model) {
        model.addAttribute("vegetablesList", productService.getProdByCategory("Vegetables"));
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "vegetables";
    }

    @GetMapping("/alcohol")
    public String getAlhocolsList(Model model) {
        model.addAttribute("alcoholList", productService.getProdByCategory("Alcohol"));
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "alcohol";
    }
}
