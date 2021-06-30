package maciej.grochowski.grocerystore.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
    @DecimalMin(value = "0.00", message = "Price has to be a non negative number")
    private BigDecimal price;

    @NotNull
    @Column(name = "amount")
    @DecimalMin(value = "0", message = "Amount has to be a non negative number")
    private BigDecimal amount = BigDecimal.valueOf(10);

    @NotNull
    @Length(min = 1, max = 50, message = "Your email must consist of 6 to 30 letters.")
    @Column(name = "category")
    private String category;

    @Column(name = "country_prod")
    private String countryProd;

    @Column(name = "date_expiration")
    private Date dateExpiration;

    public Product(Integer id, String name, String brand, BigDecimal price, String category, String countryProd, Date dateExpiration) {
        this.id = id;
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.category = category;
        this.countryProd = countryProd;
        this.dateExpiration = dateExpiration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return name.equals(product.name) && Objects.equals(brand, product.brand) && price.equals(product.price) && category.equals(product.category) && Objects.equals(countryProd, product.countryProd) && Objects.equals(dateExpiration, product.dateExpiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brand, price, category, countryProd, dateExpiration);
    }

    public void setPrice(BigDecimal price) {
        this.price = new BigDecimal(String.valueOf(price));
    }
}