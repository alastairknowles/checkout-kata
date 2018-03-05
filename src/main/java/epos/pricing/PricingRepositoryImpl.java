package epos.pricing;

import com.google.common.collect.ImmutableMap;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class PricingRepositoryImpl implements PricingRepository {

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
