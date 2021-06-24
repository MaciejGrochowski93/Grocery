package maciej.grochowski.grocerystore.product;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
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

    @NotNull
    @Length(min = 1, max = 6, message = "Price has to be a non negative number.")
    @Column(name = "price")
    private Double price;

    @NotNull
    @Length(min = 1, max = 50, message = "Your email must consist of 6 to 30 letters.")
    @Column(name = "category")
    private String category;

    @Column(name = "country_prod")
    private String countryProd;

    @Column(name = "date_expiration")
    private Date dateExpiration;
}