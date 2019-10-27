package cube.model.sale;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.TreeSet;

public class SalesHistory {
    @JsonProperty
    private TreeSet<Sale> salesSet;

    public SalesHistory() {
        this.salesSet = new TreeSet<Sale>(new SaleComparator());
    }

    public SalesHistory(TreeSet<Sale> salesSet) {
        this.salesSet = salesSet;
    }

}