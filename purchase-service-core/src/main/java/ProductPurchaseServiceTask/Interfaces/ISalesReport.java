package ProductPurchaseServiceTask.Interfaces;

import java.util.Date;
import java.util.List;

public interface ISalesReport {

    Date getFromDate();
    
    Date getToDate();
    
    double getTotalSales();

    List<ISoldProductSummary> getSoldProducts();
}
