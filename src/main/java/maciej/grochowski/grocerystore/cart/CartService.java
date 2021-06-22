package maciej.grochowski.grocerystore.cart;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.product.Product;
import maciej.grochowski.grocerystore.product.ProductRepository;
import maciej.grochowski.grocerystore.user.MyUserDetails;
import maciej.grochowski.grocerystore.user.User;
import maciej.grochowski.grocerystore.user.UserRepository;
import maciej.grochowski.grocerystore.user.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CartService {

    private final ProductRepository productRepository;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ArrayList<Product> cartProducts = new ArrayList<>();

    public List<Product> getAllCartProducts() {
        return cartProducts;
    }

    public void addCartProduct(Product product) {
        cartProducts.add(productRepository.findProductById(product.getId()));
    }

    public void deleteCartProduct(Product product) {
        cartProducts.remove(productRepository.findProductById(product.getId()));
    }

    public void deleteAllCartProducts() {
        cartProducts.clear();
    }

    public void buyCartProducts() {
        User user = userService.getPrincipal();

        user.setMoney(user.getMoney() - getCartProductsPrice());
        userRepository.save(user);
    }

    public double getCartProductsPrice() {
        List<Product> allCartProducts = getAllCartProducts();
        double totalPrice = 0;
        for (Product p : allCartProducts) {
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }
}
