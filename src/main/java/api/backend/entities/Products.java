package api.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productID;
    @Column(nullable = false,unique = true)
    private Long SKU;//stock unit number, which is a unique identifier for our products
    private String productName;
    private String productDescription;

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "category_ID",referencedColumnName = "categoryID",nullable = false)
    private Category category;

    public Products(Long SKU, String productName, String productDescription, Category category) {
        this.SKU = SKU;
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
    }
}
