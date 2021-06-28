package maciej.grochowski.grocerystore.cart;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.exception.NotEnoughMoneyException;
import maciej.grochowski.grocerystore.product.Product;
import maciej.grochowski.grocerystore.product.ProductRepository;
import maciej.grochowski.grocerystore.user.User;
import maciej.grochowski.grocerystore.user.UserRepository;
import maciej.grochowski.grocerystore.user.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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

    public void buyCartProducts() throws NotEnoughMoneyException {
        User user = userService.getPrincipal();
        BigDecimal userMoney = user.getMoney();
        BigDecimal cartPrice = getCartProductsPrice();

        if (userMoney.compareTo(cartPrice) == -1) {
            throw new NotEnoughMoneyException();
        }

        user.setMoney(userMoney.subtract(cartPrice));
        userRepository.save(user);
    }

    public BigDecimal getCartProductsPrice() {
        List<Product> allCartProducts = getAllCartProducts();
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (int i = 0; i < allCartProducts.size(); i++) {
            totalPrice = totalPrice.add(allCartProducts.get(i).getPrice());
        }
        return totalPrice;
    }
}
