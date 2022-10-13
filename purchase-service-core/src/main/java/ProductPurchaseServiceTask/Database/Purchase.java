package ProductPurchaseServiceTask.Database;

import java.util.Date;

import ProductPurchaseServiceTask.Interfaces.IProduct;

/**
* Each purchase object simulates a database record.
* Every purchase gets their own purchase object.
*/
public class Purchase {
    private String productId;
    private double price;
    private Date created;

    public Purchase(IProduct product) {
        this.productId = product.getProductId();
        this.price = product.getPrice();
        this.created = new Date();
    }

    public void changeCreated(Date newDate) {
        this.created = newDate;
    }

    public String getProductId() {
        return this.productId;
    }

    public double getPrice() {
        return this.price;
    }

    public Date getCreated() {
        return this.created;
    }
}
