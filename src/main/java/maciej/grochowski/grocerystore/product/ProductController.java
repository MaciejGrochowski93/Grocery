package maciej.grochowski.grocerystore.product;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.user.User;
import maciej.grochowski.grocerystore.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;
    private final UserService userService;

    @GetMapping("/addProduct")
    public String addProductForm(Model model) {
        model.addAttribute("productForm", new Product());
        return "new_product";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(@ModelAttribute("productForm") Product product) {
        productService.addProduct(product);
        return "redirect:/admin";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, Model model) {
//        Optional<Product> product = productService.getProdById(id);
        Product product = productService.findProductById(id);
        model.addAttribute("productForm", product);
        return "update_product";
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, @ModelAttribute("productForm") Product product) {
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }


    @PutMapping("/buyProductForm")
    public String buyProduct(@ModelAttribute("product") User user, Product product) {
        userService.buyProduct(user, product);
        return "new_product";
    }

    @DeleteMapping
    public String deleteProduct() {
        return null;
    }
}
