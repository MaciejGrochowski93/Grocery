package maciej.grochowski.grocerystore.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;

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

    @ApiModelProperty(value = "Email must be unique.")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "money")
    private BigDecimal money;

    @Column(name = "enabled")
    private Boolean enabled = false;

    @Column(name = "locked")
    private Boolean locked = false;

    @ApiModelProperty("ROLE_USER or ROLE_ADMIN")
    @Column(name = "roles")
    private String roles = "ROLE_USER";

    public User(String email, String password, String firstName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }
}