package maciej.grochowski.grocerystore.controller;

import io.swagger.annotations.ApiOperation;
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

    @ApiOperation(value = "ADMIN can add a new Product.")
    @GetMapping("/addProduct")
    public String addProductForm(Model model) {
        model.addAttribute("productForm", new Product());
        return "new_product";
    }

    @ApiOperation(value = "ADMIN can add a new Product.",
            notes = "You have to provide at least the name, price & category.")
    @PostMapping("/addProduct")
    public String addNewProduct(@ModelAttribute("productForm") @Valid Product product,
                                BindingResult result) {
        if (result.hasErrors()) {
            return "new_product";
        }
        productService.addProduct(product);
        return "redirect:/";
    }

    @ApiOperation(value = "ADMIN can update the existing Product.")
    @GetMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id, Model model) {
        productService.findProductById(id)
                .ifPresent(product -> model.addAttribute("productForm2", product));
        return "update_product";
    }

    @ApiOperation(value = "ADMIN can update the existing Product.",
            notes = "You have to provide at least the name, price & category.")
    @PostMapping("/update/{id}")
    public String updateProduct(@PathVariable Integer id,
                                @Valid @ModelAttribute("productForm2") Product product, BindingResult result) {
        if (result.hasErrors()) {
            return "update_product";
        } else {
            productService.updateProduct(id, product);
            return "redirect:/";
        }
    }

    @ApiOperation(value = "ADMIN can delete the existing Product.")
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
