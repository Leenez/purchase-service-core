package ProductPurchaseServiceTask.Database;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.stream.Collectors;

import ProductPurchaseServiceTask.App.Product;
import ProductPurchaseServiceTask.Interfaces.IProduct;

/**
* Simulates a database for product catalog and product purchases.
* Also contains access methods for the simulated database.
*/
public class DbAccess {
    public static HashMap<String, IProduct> products = new HashMap<>();
    public static ArrayList<Purchase> purchases = new ArrayList<>();
    
    public static void addProduct(IProduct product) {
        products.putIfAbsent(product.getProductId(), product);
    }

    public static void deleteProduct(IProduct product) {
        IProduct toDel = products.get(product.getProductId());
        if (toDel != null 
            && toDel.getProductId().equals(product.getProductId())
            && toDel.getPrice() == product.getPrice()){
                
        products.remove(toDel.getProductId());
        }
    }

    public static void purchaseProduct(IProduct purchase) {
        Purchase newPurchase = new Purchase(purchase);
        purchases.add(newPurchase);
    }

    public static ArrayList<Purchase> getProductPurchases(String productId, Date fromDate, Date toDate) {
        ArrayList<Purchase> filteredPurchases = purchases
            .stream()
            .filter(purchase -> !purchase.getCreated().before(fromDate) 
                && !purchase.getCreated().after(toDate)
                && purchase.getProductId().equals(productId))
            .collect(Collectors.toCollection(ArrayList::new));
        return filteredPurchases;
    }

    public static ArrayList<Purchase> getAllPurchases(Date fromDate, Date toDate) {
        ArrayList<Purchase> allPurchases = purchases
            .stream()
            .filter(purchase -> !purchase.getCreated().before(fromDate) && !purchase.getCreated().after(toDate))
            .collect(Collectors.toCollection(ArrayList::new));      
        return allPurchases;
    }

    public static HashMap<String, IProduct> getProductsInPurchases() {
        HashMap<String, IProduct> productsInPurchases = new HashMap<String, IProduct>();
        purchases.stream()
                 .forEach(data -> productsInPurchases.put(data.getProductId(), new Product(data.getProductId(), data.getPrice())));
        return productsInPurchases;
    }

    public static HashMap<String, IProduct> getProducts() {
        return products;
    }

}