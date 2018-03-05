package epos.checkout;

import java.util.List;

public interface CheckoutService {

    /**
     * Receives a list of checkout inputs and computes quantities and prices
     *
     * @param items The list of checkout inputs
     * @return The list of checkout outputs
     */
    CheckoutReconciliation checkout(List<CheckoutInput> items);

}
