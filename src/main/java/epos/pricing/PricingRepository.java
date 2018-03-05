package epos.pricing;

public interface PricingRepository {

    /**
     * We could assume that in an actual checkout pricing would come from a database
     * This class mocks the interface to this database
     *
     * @return A map of item codes to pricings
     */
    Pricing getCurrentPricing(String item);

}
