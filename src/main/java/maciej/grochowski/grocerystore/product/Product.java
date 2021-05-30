package maciej.grochowski.grocerystore.product;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String brand;
    private Double price;
    private String category;

    @Column(name = "country_prod")
    private String countryProd;

    @Column(name = "date_expiration")
    private Date dateExpiration;
}