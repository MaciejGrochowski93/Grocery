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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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

    @Length(min = 1, max = 50, message = "Product's name must consist of 1 to 50 letters.")
    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "brand")
    private String brand;

    @DecimalMin(value = "0.00", message = "Price has to be a non negative number")
    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @DecimalMin(value = "0", message = "Amount has to be a non negative number")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount = BigDecimal.valueOf(10);

    @Length(min = 1, max = 50, message = "Your email must consist of 6 to 30 letters.")
    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "country_prod")
    private String countryProd;

    @Column(name = "date_expiration")
    private LocalDate dateExpiration;

    public Product(Integer id, String name, String brand, BigDecimal price, String category, String countryProd, LocalDate dateExpiration) {
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
        return name.equals(product.name) && Objects.equals(brand, product.brand)
                && price.equals(product.price) && category.equals(product.category)
                && Objects.equals(countryProd, product.countryProd) && Objects.equals(dateExpiration, product.dateExpiration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, brand, price, category, countryProd, dateExpiration);
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}