package maciej.grochowski.grocerystore.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import maciej.grochowski.grocerystore.product.Product;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    private Integer id;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;
    @Column(name = "first_name")
    private String firstName;
    private Double money = 100.00;
    private Boolean enabled = false;
    private Boolean locked = false;
    private String roles = "ROLE_USER";

    public User(String email, String password, String firstName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }

    // TODO customize validation errors

    public void buyProduct(Product product) {
        this.setMoney(this.getMoney() - product.getPrice());
    }

}
