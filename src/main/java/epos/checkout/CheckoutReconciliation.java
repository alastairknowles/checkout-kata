package epos.checkout;

import java.util.ArrayList;
import java.util.List;

public class CheckoutReconciliation {

    private List<CheckoutOutput> outputs = new ArrayList<>();

    private Long total;

    private Long discount;

    public List<CheckoutOutput> getOutputs() {
        return outputs;
    }

    public CheckoutReconciliation setOutputs(List<CheckoutOutput> outputs) {
        this.outputs = outputs;
        return this;
    }

    public Long getTotal() {
        return total;
    }

    public CheckoutReconciliation setTotal(Long total) {
        this.total = total;
        return this;
    }

    public Long getDiscount() {
        return discount;
    }

    public CheckoutReconciliation setDiscount(Long discount) {
        this.discount = discount;
        return this;
    }

}
