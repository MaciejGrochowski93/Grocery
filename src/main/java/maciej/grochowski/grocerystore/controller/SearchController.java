package maciej.grochowski.grocerystore.controller;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.Product;
import maciej.grochowski.grocerystore.service.CartService;
import maciej.grochowski.grocerystore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Set;

@Controller
@AllArgsConstructor
public class SearchController {

    private final ProductService productService;
    private final CartService cartService;

    @ApiOperation(value = "Shows a set of available Product categories.")
    @GetMapping("/category")
    public String getCategorySet(Model model) {
        Set<String> categorySet = productService.getCategoriesSet();
        model.addAttribute("categorySet", categorySet);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/categorySet";
    }

    @ApiOperation(value = "Shows a set of Products from the particular category.")
    @GetMapping("/category/{category}")
    public String getParticularCategory(Model model, @PathVariable String category) {
        List<Product> productsSetByCategory = productService.getProductsByCategory(category);
        model.addAttribute("productsSetByCategory", productsSetByCategory);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/categoryParticular";
    }

    @ApiOperation(value = "Shows a set of available Product brands.")
    @GetMapping("/brand")
    public String getBrandSet(Model model) {
        Set<String> brandSet = productService.getBrandsSet();
        model.addAttribute("brandSet", brandSet);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/brandSet";
    }

    @ApiOperation(value = "Shows a set of Products from the particular brand.")
    @GetMapping("/brand/{brand}")
    public String getParticularBrand(Model model, @PathVariable String brand) {
        List<Product> productsSetByBrand = productService.getProductsByBrand(brand);
        model.addAttribute("productsSetByBrand", productsSetByBrand);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/brandParticular";
    }

    @ApiOperation(value = "Shows a set of available Product countries of production.")
    @GetMapping("/country")
    public String getCountrySet(Model model) {
        Set<String> countrySet = productService.getCountriesSet();
        model.addAttribute("countrySet", countrySet);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/countrySet";
    }

    @ApiOperation(value = "Shows a set of Products from the particular country.")
    @GetMapping("/country/{country}")
    public String getParticularCountry(Model model, @PathVariable String country) {
        List<Product> productsSetByCountry = productService.getProductsByCountry(country);
        model.addAttribute("productsSetByCountry", productsSetByCountry);
        model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
        return "searching/countryParticular";
    }

    @ApiOperation(value = "Allows you to search for Product by the given parameter.",
            notes = "You can search by name, brand, category or country.")
    @GetMapping("/search")
    public String getProductByAnything(Model model, @RequestParam("productsSetByAnything") String anything) {
        List<Product> prodByAnything = productService.getProdByAnything(anything);
        if (!prodByAnything.isEmpty()) {
            model.addAttribute("productsListByAnything", prodByAnything);
            model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
            return "searching/anything";
        } else {
            var prodNotFoundMsg = "Products not found.";
            model.addAttribute("prodNotFoundMsg", prodNotFoundMsg);
            model.addAttribute("cartProductAmount", cartService.getProductsInCartAmount());
            return "products_not_found";
        }
    }
}
