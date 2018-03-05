package epos.checkout;

import epos.pricing.Pricing;

public class CheckoutInput {

    private String item;

    private Pricing pricingOverride;

    public String getItem() {
        return item;
    }

    public CheckoutInput setItem(String item) {
        this.item = item;
        return this;
    }

    public Pricing getPricingOverride() {
        return pricingOverride;
    }

    public CheckoutInput setPricingOverride(Pricing pricingOverride) {
        this.pricingOverride = pricingOverride;
        return this;
    }

}
