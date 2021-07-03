package maciej.grochowski.grocerystore.cart;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.error.NotEnoughMoneyException;
import maciej.grochowski.grocerystore.product.Product;
import maciej.grochowski.grocerystore.product.ProductRepository;
import maciej.grochowski.grocerystore.user.MyUserDetails;
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
        var foundProd = productRepository.findProductById(product.getId());
        var newCartProduct = new Product(foundProd.getId(), foundProd.getName(), foundProd.getBrand(),
                foundProd.getPrice(), BigDecimal.ONE, foundProd.getCategory(),
                foundProd.getCountryProd(), foundProd.getDateExpiration());

        if (!cartProducts.contains(newCartProduct)) {
            cartProducts.add(newCartProduct);
        }
    }

    public void buyCartProducts(MyUserDetails userDetails) throws NotEnoughMoneyException {
        User user = userService.getPrincipal();
        BigDecimal userMoney = user.getMoney();
        BigDecimal cartPrice = getCartProductsPrice();

        if (userMoney.compareTo(cartPrice) < 0) {
            throw new NotEnoughMoneyException();
        } else {
            user.setMoney(userMoney.subtract(cartPrice));
            userDetails.setMoney(user.getMoney());
            userRepository.save(user);
            deleteAllCartProducts();
        }
    }

    public String getCartProductsAmount() {
        if (cartProducts.isEmpty()) {
            return "";
        } else {
            return " (" + cartProducts.size() + ")";
        }
    }

    public BigDecimal getCartProductsPrice() {
        List<Product> allCartProducts = getAllCartProducts();
        BigDecimal totalPrice = BigDecimal.ZERO;

        for (Product particularProduct : allCartProducts) {
            totalPrice = totalPrice.add(
                    particularProduct.getPrice()
                            .multiply(particularProduct.getAmount()));
        }
        return totalPrice;
    }

    public void oneMoreSameProduct(Product product) {
        cartProducts.stream()
                .filter(updatedProd -> updatedProd.getId().equals(product.getId()))
                .findFirst()
                .ifPresent(updatedProd -> updatedProd.setAmount(updatedProd.getAmount().add(BigDecimal.ONE)));
    }

    public void oneLessSameProduct(Product product) {
        cartProducts.stream()
                .filter(updatedProd -> updatedProd.getId().equals(product.getId()) && updatedProd.getAmount().compareTo(BigDecimal.ONE) > 0)
                .findFirst()
                .ifPresent(updatedProd -> updatedProd.setAmount(updatedProd.getAmount().subtract(BigDecimal.ONE)));
    }

    public void deleteCartProduct(Product product) {
        cartProducts.remove(productRepository.findProductById(product.getId()));
    }

    public void deleteAllCartProducts() {
        cartProducts.clear();
    }
}
