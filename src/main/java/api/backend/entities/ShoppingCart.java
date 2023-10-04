package api.backend.entities;

public class ShoppingCart {
    //shopping cart must be linked with the customer
    private Long productID;
    private String productName;
    private Long quantity;
    private Long totalCost;

    private Customer customer;//who is buying the product
}
