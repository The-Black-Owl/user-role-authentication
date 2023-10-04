package api.backend.entities;

public class Customer {
    //the customer must extend
    private Long customerID;
    private String customerName;

    private String customerEmail;

    //where customer is located
    private Address adress;

    //one customer one cart
    private ShoppingCart cart;
}
