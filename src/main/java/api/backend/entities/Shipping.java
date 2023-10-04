package api.backend.entities;

import java.util.Date;

public class Shipping {
    private Long shippingID;
    private Date shippingDate;

    //one shipment can have manny addresses
    private Address shippingAddress;

    //one shipment can ship many shopping carts
    private ShoppingCart cart;
}
