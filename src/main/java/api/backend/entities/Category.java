package api.backend.entities;

public class Category {
    private Long categoryID;
    private String categoryType;

    //the category must be linked with the product
    //One can have many products in one category
    private Product product;
}
