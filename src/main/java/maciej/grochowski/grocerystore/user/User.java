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

//    @NotEmpty(message = "Please, provide your email.")
//    @Size(min = 6, max = 30)
    @Length(min = 6, max = 30, message = "Email length must be between 6 - 30")
    @Column(name = "email")
    private String email;

    //    @NotEmpty(message = "Please, set your password.")
//    @Size(min = 6, max = 30)
    @Column(name = "password")
    private String password;

    //    @NotEmpty(message = "Please, provide your name.")
//    @Size(min = 1, max = 30)
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