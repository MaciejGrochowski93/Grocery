package maciej.grochowski.grocerystore.user;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name = "user")
@DynamicUpdate
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @Column(name = "id")
    private Integer id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "money")
    private Double money = 100.00;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @Column(name = "locked")
    private Boolean locked = false;

    @Column(name = "roles")
    private String roles = "ROLE_USER";

    public User(String email, String password, String firstName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }
}