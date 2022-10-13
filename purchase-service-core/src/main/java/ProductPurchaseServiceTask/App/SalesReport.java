package ProductPurchaseServiceTask.App;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import ProductPurchaseServiceTask.Database.DbAccess;
import ProductPurchaseServiceTask.Interfaces.IProduct;
import ProductPurchaseServiceTask.Interfaces.ISalesReport;
import ProductPurchaseServiceTask.Interfaces.ISoldProductSummary;

public class SalesReport implements ISalesReport{
    private Date fromDate;
    private Date toDate;

    public SalesReport(Date _fromDate, Date _toDate) {
        this.fromDate = _fromDate;
        this.toDate = _toDate;
    }

    @Override
    public Date getFromDate() {
        return this.fromDate;
    }

    @Override
    public Date getToDate() {
        return this.toDate;
    }

    @Override
    public double getTotalSales() {
        double totalSales = DbAccess.getAllPurchases(this.fromDate, this.toDate)
            .stream()
            .mapToDouble(item -> item.getPrice())
            .sum();   
        return totalSales;
    }

    @Override
    public List<ISoldProductSummary> getSoldProducts() {
        HashMap<String, IProduct> products = DbAccess.getProductsInPurchases();
        List<ISoldProductSummary> summaryOfSoldProducts = new ArrayList<ISoldProductSummary>();
        
        for (String productId : products.keySet()) {
            summaryOfSoldProducts.add(new SoldProductSummary(productId, DbAccess.getProductPurchases(productId, fromDate, toDate)));
        }     
        return summaryOfSoldProducts;
    }
    
}
