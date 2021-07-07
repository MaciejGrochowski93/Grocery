package maciej.grochowski.grocerystore.controller;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.Product;
import maciej.grochowski.grocerystore.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ProductController implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/results").setViewName("results");
    }

    private final ProductService productService;

    @GetMapping("/addProduct")
    public String addProductForm(Model model) {
        model.addAttribute("productForm", new Product());
        return "new_product";
    }

    @PostMapping("/addProduct")
    public String addNewProduct(@ModelAttribute("productForm") @Valid Product product,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "new_product";
        }
        productService.addProduct(product);
        return "redirect:/";
    }

    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, Model model) {
        productService.findProductById(id)
                .ifPresent(product -> model.addAttribute("productForm2", product));
        return "update_product";
    }

    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id,
                                @ModelAttribute("productForm2") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "update_product";
        } else {
            productService.updateProduct(id, product);
            return "redirect:/";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
