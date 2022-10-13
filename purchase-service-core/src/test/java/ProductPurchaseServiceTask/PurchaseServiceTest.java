package ProductPurchaseServiceTask;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

import ProductPurchaseServiceTask.App.Product;
import ProductPurchaseServiceTask.App.PurchaseService;
import ProductPurchaseServiceTask.Database.DbAccess;
import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;

public class PurchaseServiceTest {

    PurchaseService purchaseService = new PurchaseService();

    @Before
    public void init() {
        DbAccess.purchases.clear();
        DbAccess.products.clear();
    }

    @Test(expected = Exception.class)
    public void addNewProductThrowsExceptionWithNegativePrice() throws Exception {  
        purchaseService.addNewProduct("product_1", -1);
    }

    @Test
    public void addNewProductAddsNewIProduct() throws Exception {
        purchaseService.addNewProduct("product_1", 1);
        assertTrue(DbAccess.products.get("product_1") instanceof IProduct);
    }

    @Test
    public void removeProductRemovesExistingProduct() {
        DbAccess.products.put("product_1", new Product("product_1", 2));
        DbAccess.products.put("product_2", new Product("product_2", 3));
        
        purchaseService.removeProduct(new Product("nonexisting", 4));
        assertEquals(2, DbAccess.products.size());

        purchaseService.removeProduct(new Product("product_1", 2));
        assertEquals(1, DbAccess.products.size());
    }

    @Test(expected = Exception.class)
    public void purchaseProductThrowsExceptionForNonExistingProduct() throws Exception {
        IProduct product_1 = new Product("product_1", 2.5);
        IProduct product_2 = new Product("product_2", 2.5);
        DbAccess.products.put("product_1", product_1);
        
        purchaseService.purchaseProduct(product_2);
    }

    @Test
    public void purchaseProductAddsProductPurchase() throws Exception {
        IProduct product_1 = new Product("product_1", 2.5);
        DbAccess.products.put("product_1", product_1);

        purchaseService.purchaseProduct(product_1);
        assertEquals("product_1", DbAccess.products.get("product_1").getProductId());

        purchaseService.purchaseProduct(product_1);
        assertEquals(2.5, DbAccess.products.get("product_1").getPrice(), 0.01);
    }

    @Test
    public void getAvailableProductsReturnsAllProducts() {
        IProduct product_1 = new Product("product_1", 2.5);
        IProduct product_2 = new Product("product_2", 4.5);
        DbAccess.products.put("product_1", product_1);
        DbAccess.products.put("product_2", product_2);

        assertEquals(2, purchaseService.getAvailableProducts().size());
        assertEquals(product_1, purchaseService.getAvailableProducts().get(1));
        assertEquals(product_2, purchaseService.getAvailableProducts().get(0));

    }

    @Test
    public void getSalesReportReturnsIsalesReportWithInTimePeriod() {
        Calendar calendar_1 = new GregorianCalendar(2022,0,27);
        Calendar calendar_2 = new GregorianCalendar(2022,0,28);
        
        var salesReport = purchaseService.getSalesReport(calendar_1.getTime(), calendar_2.getTime());
        
        assertEquals(calendar_1.getTime(), salesReport.getFromDate());
        assertEquals(calendar_2.getTime(), salesReport.getToDate());
        assertTrue(salesReport instanceof ISalesReport);
    }
    
}
