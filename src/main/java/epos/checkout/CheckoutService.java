package epos.checkout;

import java.util.List;

public interface CheckoutService {

    /**
     * Receives a list of checkout items and computes quantities and prices
     *
     * @param items The list of checkout items, identified by code
     * @return The list of checkout items, quantities and prices
     */
    List<Checkout> checkout(List<String> items);

}
