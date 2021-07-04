package maciej.grochowski.grocerystore.controller;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.Product;
import maciej.grochowski.grocerystore.service.CartService;
import maciej.grochowski.grocerystore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Set;

@Controller
@AllArgsConstructor
public class SearchController {

    private final ProductService productService;
    private final CartService cartService;

    @GetMapping("/category")
    public String getCategorySet(Model model) {
        Set<String> categorySet = productService.getCategorySet();
        model.addAttribute("categorySet", categorySet);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/categorySet";
    }

    @GetMapping("/category/{category}")
    public String getParticularCategory(Model model, @PathVariable String category) {
        Set<Product> productsSetByCategory = productService.getProductsByCategory(category);
        model.addAttribute("productsSetByCategory", productsSetByCategory);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/categoryParticular";
    }

    @GetMapping("/brand")
    public String getBrandSet(Model model) {
        Set<String> brandSet = productService.getBrandSet();
        model.addAttribute("brandSet", brandSet);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/brandSet";
    }

    @GetMapping("/brand/{brand}")
    public String getParticularBrand(Model model, @PathVariable String brand) {
        Set<Product> productsSetByBrand = productService.getProductsByBrand(brand);
        model.addAttribute("productsSetByBrand", productsSetByBrand);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/brandParticular";
    }

    @GetMapping("/country")
    public String getCountrySet(Model model) {
        Set<String> countrySet = productService.getCountrySet();
        model.addAttribute("countrySet", countrySet);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/countrySet";
    }

    @GetMapping("/country/{country}")
    public String getParticularCountry(Model model, @PathVariable String country) {
        Set<Product> productsSetByCountry = productService.getProductsByCountry(country);
        model.addAttribute("productsSetByCountry", productsSetByCountry);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/countryParticular";
    }

    @GetMapping("/search")
    public String getProductByAnything(Model model, @RequestParam (value="productsSetByAnything", required = false) String anything) {
        Set<Product> productsSetByAnything = productService.getProdByAnything(anything);
        model.addAttribute("productsSetByAnything", productsSetByAnything);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/anything";
    }
}
