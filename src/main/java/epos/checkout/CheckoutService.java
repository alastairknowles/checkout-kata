package epos.checkout;

import epos.pricing.Pricing;

import java.util.List;

public interface CheckoutService {

    /**
     * Receives a list of checkout inputs and computes quantities and prices
     *
     * @param inputs The list of checkout inputs
     * @return The list of checkout outputs
     */
    CheckoutReconciliation checkout(List<CheckoutInput> inputs);

    /**
     * Receives a list of checkout inputs and computes quantities and prices
     *
     * @param inputs    The list of checkout inputs
     * @param pricings The list of pricings to override
     * @return The list of checkout outputs
     */
    CheckoutReconciliation checkout(List<CheckoutInput> inputs, List<Pricing> pricings);

}
