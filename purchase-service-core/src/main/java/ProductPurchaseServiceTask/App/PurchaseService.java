package ProductPurchaseServiceTask.App;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import ProductPurchaseServiceTask.Database.DbAccess;
import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.IPurchaseService;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;

public class PurchaseService implements IPurchaseService{

    public PurchaseService() {}

    @Override
    public void addNewProduct(String productId, double price) throws Exception {
        if (price < 0) {
            throw new Exception("Can't add product with negative price");
        }
        DbAccess.addProduct(new Product(productId, price));
    }

    @Override
    public void removeProduct(IProduct product) {
        DbAccess.deleteProduct(product);
    }

    @Override
    public List<IProduct> getAvailableProducts() {
        ArrayList<IProduct> productList = DbAccess.getProducts()
            .values()
            .stream()
            .collect(Collectors.toCollection(ArrayList::new));
        return productList;
    }

    @Override
    public void purchaseProduct(IProduct purchase) throws Exception {
        if (!getAvailableProducts().contains(purchase)) {
            throw new Exception("Product not found");
        }
        DbAccess.purchaseProduct(purchase);
    }

    @Override
    public ISalesReport getSalesReport(Date fromDate, Date toDate) {
        ISalesReport salesReport = new SalesReport(fromDate, toDate);
        return salesReport;
    }
}
