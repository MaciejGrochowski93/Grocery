package maciej.grochowski.grocerystore.product;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> findProductById(Integer id) {
        return productRepository.findById(id);
    }

    public List<Product> getProdByName(String name) {
        ArrayList<Product> products = new ArrayList<>();
        productRepository
                .findAll()
                .stream()
                .filter(p -> p.getName().toLowerCase().contains(name.toLowerCase()))
                .forEach(products::add);
        return products;
    }

    public List<Product> getProdByCategory(String category) {
        ArrayList<Product> products = new ArrayList<>();
        productRepository
                .findAll()
                .stream()
                .filter(e -> e.getCategory().equalsIgnoreCase(category))
                .forEach(products::add);
        return products;
    }

    public void addProduct(Product product) {
        productRepository.save(product);
    }

    public void updateProduct(Integer id, Product product) {
        productRepository.save(product);
    }

//    public void deleteProduct(Integer id) {
//        productRepository.deleteById(id);
//    }
}
