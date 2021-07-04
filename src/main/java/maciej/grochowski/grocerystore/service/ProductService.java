package maciej.grochowski.grocerystore.service;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.Product;
import maciej.grochowski.grocerystore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public Set<Product> getAllProducts() {
        return new HashSet<>(productRepository.findAll());
    }

    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    public Set<Product> getProdByAnything(String anything) {
        return productRepository
                .findAll()
                .stream()
                .filter(product -> product.toString().toLowerCase().contains(anything.toLowerCase()))
                .collect(Collectors.toSet());
    }

    public Set<String> getCategorySet() {
        return getAllProducts()
                .stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
    }

    public Set<Product> getProductsByCategory(String category) {
        return productRepository
                .findAll()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toSet());
    }

    public Set<String> getBrandSet() {
        return getAllProducts()
                .stream()
                .filter(product -> product.getBrand() != null)
                .map(Product::getBrand)
                .collect(Collectors.toSet());
    }

    public Set<Product> getProductsByBrand(String brand) {
        return productRepository
                .findAll()
                .stream()
                .filter(product -> product.getBrand() != null && product.getBrand().equals(brand))
                .collect(Collectors.toSet());
    }

    public Set<String> getCountrySet() {
        return getAllProducts()
                .stream()
                .map(Product::getCountryProd)
                .collect(Collectors.toSet());
    }

    public Set<Product> getProductsByCountry(String country) {
        return productRepository
                .findAll()
                .stream()
                .filter(product -> product.getCountryProd().equals(country))
                .collect(Collectors.toSet());
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Integer id, Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Integer id) {
        findProductById(id).ifPresent(product -> productRepository.deleteById(id));
    }
}
