package maciej.grochowski.grocerystore.product;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.user.User;
import maciej.grochowski.grocerystore.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        productService.findProductById(id)
                .ifPresent(product -> model.addAttribute("productForm2", product));
        return "update_product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, @ModelAttribute("productForm2") Product product) {
        productService.updateProduct(id, product);
        return "redirect:/admin";
    }

//    @GetMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable Integer id, Model model) {
//        productService.findProductById(id)
//                .ifPresent(product -> model.addAttribute("productForm3", product));
//        return "redirect:/admin";
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public String deleteProduct(@PathVariable Integer id) {
//        productService.deleteProduct(id);
//        return "redirect:/admin";
//    }

    @PutMapping("/buyProductForm")
    public String buyProduct(@ModelAttribute("product") User user, Product product) {
        userService.buyProduct(user, product);
        return "new_product";
    }
}
