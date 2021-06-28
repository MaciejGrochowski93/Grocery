package maciej.grochowski.grocerystore.product;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Length(min = 1, max = 50, message = "Product's name must consist of 1 to 50 letters.")
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "brand")
    private String brand;

    @Column(name = "price", nullable = false)
    @DecimalMin(value = "0.00", message = "*Price has to be non negative number")
    private BigDecimal price;

    @NotNull
    @Length(min = 1, max = 50, message = "Your email must consist of 6 to 30 letters.")
    @Column(name = "category")
    private String category;

    @Column(name = "country_prod")
    private String countryProd;

    @Column(name = "date_expiration")
    private Date dateExpiration;
}