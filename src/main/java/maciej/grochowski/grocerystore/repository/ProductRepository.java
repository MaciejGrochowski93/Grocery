package maciej.grochowski.grocerystore.repository;

import maciej.grochowski.grocerystore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Product findProductById(Integer id);
}
