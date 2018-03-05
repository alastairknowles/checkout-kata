package epos.pricing;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class PricingRepositoryImpl implements PricingRepository {

    /**
     * We could assume that in an actual checkout pricing would come from a database
     * This class mocks the interface to this database
     *
     * @return A map of item codes to pricings
     */

    @Override
    public Pricing getCurrentPricing(String item) {
        Map<String, Pricing> pricing = ImmutableMap.<String, Pricing>builder()
                .put("A", new Pricing().setItem("A").setPrice(100L))
                .put("B", new Pricing().setItem("B").setPrice(125L))
                .put("C", new Pricing().setItem("C").setPrice(150L)
                        .setOffer(new Pricing.Offer().setMultiple(3L).setPrice(400L)))
                .build();

        return pricing.get(item);
    }

}
