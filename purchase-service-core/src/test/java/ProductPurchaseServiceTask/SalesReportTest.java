package ProductPurchaseServiceTask;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import ProductPurchaseServiceTask.App.Product;
import ProductPurchaseServiceTask.App.SalesReport;
import ProductPurchaseServiceTask.Database.DbAccess;
import ProductPurchaseServiceTask.Database.Purchase;
import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

public class SalesReportTest {

    Calendar calendar_1 = new GregorianCalendar(2022,0,27);
    Calendar calendar_2 = new GregorianCalendar(2022,0,28);
    Calendar calendar_3 = new GregorianCalendar(2022,0,29);
    Calendar calendar_4 = new GregorianCalendar(2022,0,30);
    Calendar calendar_5 = new GregorianCalendar(2022,0,31);

    @Before
    public void init() {
        DbAccess.purchases.clear();
        DbAccess.products.clear();
    }
    
    @Test
    public void getFromDateReturnsFirstConstructorParameterAsDateType() {
        SalesReport salesReport = new SalesReport(calendar_1.getTime(), calendar_2.getTime());

        assertTrue(salesReport.getFromDate() instanceof Date);
        assertEquals(calendar_1.getTime(), salesReport.getFromDate());
    }

    @Test
    public void getToDateReturnsSecondConstructorParameterAsDateType() {
        SalesReport salesReport = new SalesReport(calendar_1.getTime(), calendar_2.getTime());
        
        assertTrue(salesReport.getToDate() instanceof Date);
        assertEquals(calendar_2.getTime(), salesReport.getToDate());   
    }

    @Test
    public void getTotalSalesReturnsSumOfAllSalesInGivenTimePeriod() {
        Purchase purchase_1 = new Purchase(new Product("product_1", 1.1));
        purchase_1.changeCreated(calendar_1.getTime());
        Purchase purchase_2 = new Purchase(new Product("product_2", 1.1));
        purchase_2.changeCreated(calendar_2.getTime());
        Purchase purchase_3 = new Purchase(new Product("product_3", 1.1));
        purchase_3.changeCreated(calendar_3.getTime());

        DbAccess.purchases.add(purchase_1);
        DbAccess.purchases.add(purchase_2);
        DbAccess.purchases.add(purchase_3);

        SalesReport salesReport = new SalesReport(calendar_2.getTime(), calendar_3.getTime());
        assertEquals(2.2, salesReport.getTotalSales(), 0.001);
    }

    @Test
    public void getTotalSalesReturnsZeroForEmptyList() {
        SalesReport salesReport = new SalesReport(calendar_1.getTime(), calendar_3.getTime());
        assertEquals(0, salesReport.getTotalSales(), 0);
    }

    @Test
    public void getSoldProductsReturnsListOfIsoldProductSummariesInGivenTimePeriod() {
        Purchase purchase_1 = new Purchase(new Product("product_1", 1.1));
        purchase_1.changeCreated(calendar_1.getTime());
        Purchase purchase_2 = new Purchase(new Product("product_2", 1.1));
        purchase_2.changeCreated(calendar_2.getTime());
        Purchase purchase_3 = new Purchase(new Product("product_3", 1.1));
        purchase_3.changeCreated(calendar_3.getTime());
        Purchase purchase_4 = new Purchase(new Product("product_4", 1.1));
        purchase_4.changeCreated(calendar_4.getTime());
        Purchase purchase_5 = new Purchase(new Product("product_5", 1.1));
        purchase_5.changeCreated(calendar_5.getTime());

        DbAccess.purchases.add(purchase_1);
        DbAccess.purchases.add(purchase_2);
        DbAccess.purchases.add(purchase_3);
        DbAccess.purchases.add(purchase_4);
        DbAccess.purchases.add(purchase_5);

        SalesReport salesReport = new SalesReport(calendar_2.getTime(), calendar_4.getTime());
        List<ISoldProductSummary> report = salesReport.getSoldProducts();
        
        ISoldProductSummary summary_1 = report.get(0);
        ISoldProductSummary summary_2 = report.get(1);
        ISoldProductSummary summary_3 = report.get(2);
        ISoldProductSummary summary_4 = report.get(3);
        ISoldProductSummary summary_5 = report.get(4);

        HashMap<String, Integer> testMap = new HashMap<String, Integer>();
        testMap.put(summary_1.getProductId(), summary_1.getSoldAmount());
        testMap.put(summary_2.getProductId(), summary_2.getSoldAmount());
        testMap.put(summary_3.getProductId(), summary_3.getSoldAmount());
        testMap.put(summary_4.getProductId(), summary_4.getSoldAmount());
        testMap.put(summary_5.getProductId(), summary_5.getSoldAmount());

        assertEquals((Integer) 0, testMap.get("product_1"));
        assertEquals((Integer) 1, testMap.get("product_2"));
        assertEquals((Integer) 1, testMap.get("product_3"));
        assertEquals((Integer) 1, testMap.get("product_4"));
        assertEquals((Integer) 0, testMap.get("product_5"));
    }
}
