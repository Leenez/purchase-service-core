package ProductPurchaseServiceTask.App;

import ProductPurchaseServiceTask.Interfaces.IProduct;

public class Product implements IProduct{
    private String productId;
    private double price;
    
    public Product(String _productId, double _price){
        this.price = _price;
        this.productId = _productId;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public String getProductId() {
        return this.productId;
    }
}
