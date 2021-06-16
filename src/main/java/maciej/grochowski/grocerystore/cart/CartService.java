package maciej.grochowski.grocerystore.cart;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import maciej.grochowski.grocerystore.product.Product;
import maciej.grochowski.grocerystore.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {

    @Autowired
    private ProductRepository productRepository;
    private ArrayList<Product> cartProducts = new ArrayList<>();

    public List<Product> getAllCartProducts() {
        return cartProducts;
    }

    public void addCartProduct(Product product) {
        cartProducts.add(productRepository.findProductById(product.getId()));
    }

    public void deleteCartProduct(Product product) {
        cartProducts.remove(productRepository.findProductById(product.getId()));
    }

    public void buyCartProduct(Product product) {
        Product boughtProduct = cartProducts.get(product.getId());
        Double productPrice = boughtProduct.getPrice();

    }
}
