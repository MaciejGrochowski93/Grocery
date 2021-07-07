package maciej.grochowski.grocerystore.service;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.model.Product;
import maciej.grochowski.grocerystore.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

//    public Set<Product> getAllProducts() {
//        List<Product> productList = productRepository.findAll();
//        productList.sort(Comparator.comparing(Product::getId));
//        return new LinkedHashSet<>(productList);
//    }

    public List<Product> getAllProducts() {
        List<Product> productList = productRepository.findAll();
        return new LinkedList(productList);
    }

    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    public List<Product> getProdByAnything(String anything) {
        return getAllProducts()
                .stream()
                .filter(product -> product != null &&
                        (
                               product.getName().toLowerCase().contains(anything.toLowerCase())
                            || product.getCategory().toLowerCase().contains(anything.toLowerCase())
                            || product.getCountryProd().toLowerCase().contains(anything.toLowerCase()))
                            || (product.getBrand() != null && product.getBrand().toLowerCase().contains(anything.toLowerCase()))
                        )
                .collect(Collectors.toList());
    }

    public Set<String> getCategoriesSet() {
        return getAllProducts()
                .stream()
                .map(Product::getCategory)
                .collect(Collectors.toSet());
    }

    public List<Product> getProductsByCategory(String category) {
        return getAllProducts()
                .stream()
                .filter(product -> product.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public Set<String> getBrandsSet() {
        return getAllProducts()
                .stream()
                .filter(product -> product.getBrand() != null)
                .map(Product::getBrand)
                .collect(Collectors.toSet());
    }

    public List<Product> getProductsByBrand(String brand) {
        return getAllProducts()
                .stream()
                .filter(product -> product.getBrand() != null && product.getBrand().equals(brand))
                .collect(Collectors.toList());
    }

    public Set<String> getCountriesSet() {
        return getAllProducts()
                .stream()
                .map(Product::getCountryProd)
                .collect(Collectors.toSet());
    }

    public List<Product> getProductsByCountry(String country) {
        return getAllProducts()
                .stream()
                .filter(product -> product.getCountryProd().equals(country))
                .collect(Collectors.toList());
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
