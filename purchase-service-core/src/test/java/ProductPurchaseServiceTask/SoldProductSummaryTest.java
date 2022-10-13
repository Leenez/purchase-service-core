package ProductPurchaseServiceTask;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

import ProductPurchaseServiceTask.App.Product;
import ProductPurchaseServiceTask.App.SoldProductSummary;
import ProductPurchaseServiceTask.Database.Purchase;

public class SoldProductSummaryTest {

    @Test
    public void getProuductIdReturnsProductId() {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();
        SoldProductSummary soldProductSummary = new SoldProductSummary("p1", purchases);
        assertEquals("p1", soldProductSummary.getProductId());
    }
    
    @Test
    public void getSoldAmountRerunsCorrectNumberOfItems() {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();

        Purchase purchase_1 = new Purchase(new Product("p1", 3.2));
        Purchase purchase_2 = new Purchase(new Product("p1", 3.2));
        Purchase purchase_3 = new Purchase(new Product("p1", 3.2));
        Purchase purchase_4 = new Purchase(new Product("p1", 4));

        purchases.add(purchase_1);
        purchases.add(purchase_2);
        purchases.add(purchase_3);
        purchases.add(purchase_4);

        SoldProductSummary soldProductSummary = new SoldProductSummary("p1", purchases);
        assertEquals(4, soldProductSummary.getSoldAmount());
    }

    @Test
    public void getSoldAmountRerunsZeroForEmptyList() {
        ArrayList<Purchase> emptyList = new ArrayList<Purchase>();
        
        SoldProductSummary soldProductSummary = new SoldProductSummary("p1", emptyList);
        assertEquals(0, soldProductSummary.getSoldAmount());
    }

    @Test
    public void getTotalPriceReturnsTheSumOfAllPrices() {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();

        Purchase purchase_1 = new Purchase(new Product("p1", 3.2));
        Purchase purchase_2 = new Purchase(new Product("p1", 3.2));
        Purchase purchase_3 = new Purchase(new Product("p1", 3.2));
        Purchase purchase_4 = new Purchase(new Product("p1", 4));

        purchases.add(purchase_1);
        purchases.add(purchase_2);
        purchases.add(purchase_3);
        purchases.add(purchase_4);

        SoldProductSummary soldProductSummary = new SoldProductSummary("p1", purchases);
        assertEquals(13.6, soldProductSummary.getTotalPrice(), 0.0001);
    }

    @Test
    public void getTotalPriceReturnsZeroForEmptyList() {
        ArrayList<Purchase> purchases = new ArrayList<Purchase>();

        SoldProductSummary soldProductSummary = new SoldProductSummary("p1", purchases);
        assertEquals(0, soldProductSummary.getTotalPrice(), 0);
    }
}
