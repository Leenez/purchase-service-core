package ProductPurchaseServiceTask.App;

import java.util.ArrayList;

import ProductPurchaseServiceTask.Database.Purchase;
import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

public class SoldProductSummary implements ISoldProductSummary{
    private String productId;
    private ArrayList<Purchase> purchases;

    public SoldProductSummary(String _productId, ArrayList<Purchase> _purchases){
        this.productId = _productId;
        this.purchases = _purchases;
    }

    @Override
    public int getSoldAmount() {
        Integer soldAmount = this.purchases.size();      
        return soldAmount;
    }

    @Override
    public double getTotalPrice() {
        double totalPrice = this.purchases
            .stream()
            .mapToDouble(purchase -> purchase.getPrice())
            .sum();     
        return totalPrice;
    }

    @Override
    public String getProductId() {
        return this.productId;
    }
}
