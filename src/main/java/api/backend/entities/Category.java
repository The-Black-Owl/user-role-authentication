package api.backend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryID;
    private String categoryName;

    @OneToMany(fetch= FetchType.EAGER ,mappedBy="category")
    private Set<Products> products;

    public Category(String categoryName) {
        this.categoryName = categoryName;
    }
}
