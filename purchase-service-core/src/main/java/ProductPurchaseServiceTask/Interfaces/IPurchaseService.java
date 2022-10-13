package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;
import java.util.List;

public interface IPurchaseService {
    
    void addNewProduct(String productId, double price) throws Exception;
    
    void removeProduct(IProduct product);
    
    List<IProduct> getAvailableProducts();
    
    void purchaseProduct(IProduct product) throws Exception;

    ISalesReport getSalesReport(Date fromDate, Date toDate);
}
