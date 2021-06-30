package maciej.grochowski.grocerystore.cart;

import lombok.AllArgsConstructor;
import maciej.grochowski.grocerystore.error.NotEnoughMoneyException;
import maciej.grochowski.grocerystore.product.Product;
import maciej.grochowski.grocerystore.product.ProductRepository;
import maciej.grochowski.grocerystore.user.User;
import maciej.grochowski.grocerystore.user.UserRepository;
import maciej.grochowski.grocerystore.user.UserService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public Optional<Product> getOneProduct(int id) {
        return cartProducts.stream()
                .filter(p -> p.getId() == (id))
                .findAny();
    }

    public void addCartProduct(Product product) {
        var p = productRepository.findProductById(product.getId());
        var newCartProduct = new Product(p.getId(), p.getName(), p.getBrand(),
                p.getPrice(), BigDecimal.ONE, p.getCategory(),
                p.getCountryProd(), p.getDateExpiration());
        if (!cartProducts.contains(newCartProduct)) {
            cartProducts.add(newCartProduct);
        }
    }

    public void buyCartProducts() throws NotEnoughMoneyException {
        User user = userService.getPrincipal();
        BigDecimal userMoney = user.getMoney();
        BigDecimal cartPrice = getCartProductsPrice();

        if (userMoney.compareTo(cartPrice) < 0) {
            throw new NotEnoughMoneyException();
        } else {
            user.setMoney(userMoney.subtract(cartPrice));
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
        for (int i = 0; i < allCartProducts.size(); i++) {
            totalPrice = totalPrice.add(
                    allCartProducts.get(i).getPrice()
                            .multiply(allCartProducts.get(i).getAmount()));
        }
        return totalPrice;
    }

    public void oneMoreSameProduct(Product product) {
        var editedProduct = cartProducts.get(product.getId() - 1);
        editedProduct.setAmount(editedProduct.getAmount().add(BigDecimal.ONE));
            getOneProduct(product.getId());

        cartProducts.set(editedProduct.getId() - 1, editedProduct);
    }

    public void oneLessSameProduct(Product product) {
        var editedProduct = cartProducts.get(product.getId() - 1);
        var currentAmount = editedProduct.getAmount();

        if (currentAmount.compareTo(BigDecimal.ONE) > 0) {
            editedProduct.setAmount(currentAmount.subtract(BigDecimal.ONE));
            cartProducts.set(product.getId() - 1, editedProduct);
        }
    }

    public void deleteCartProduct(Product product) {
        cartProducts.remove(productRepository.findProductById(product.getId()));
    }

    public void deleteAllCartProducts() {
        cartProducts.clear();
    }
}
