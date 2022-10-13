package ProductPurchaseServiceTask;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import ProductPurchaseServiceTask.App.Product;

public class ProductTest {

    Product testProduct = new Product("testId", 3.2);
    
    @Test
    public void productIdIsString() {
        assertTrue(testProduct.getProductId() instanceof String);
    }

    @Test
    public void productPriceIsDouble() {
        assertTrue(Double.valueOf(testProduct.getPrice()) instanceof Double);
    }
}
