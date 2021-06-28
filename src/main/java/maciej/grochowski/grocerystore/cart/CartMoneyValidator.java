//package maciej.grochowski.grocerystore.cart;
//
//import lombok.AllArgsConstructor;
//import maciej.grochowski.grocerystore.product.Product;
//import maciej.grochowski.grocerystore.user.User;
//import maciej.grochowski.grocerystore.user.UserService;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class CartMoneyValidator {
//
//    private final UserService userService;
//
//    public String validateMoney(Product product) {
//        User user = userService.getPrincipal();
//        String message = "";
//        if (user.getMoney() > product.getPrice()) {
//                message = "You don't have enough money to buy the products. Validator.";
//        }
//        return message;
//    }
//}
