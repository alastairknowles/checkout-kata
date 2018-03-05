package epos.pricing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PricingServiceImpl implements PricingService {

    private final PricingRepository pricingRepository;

    @Autowired
    public PricingServiceImpl(PricingRepository pricingRepository) {
        this.pricingRepository = pricingRepository;
    }

    @Override
    public Pricing getCurrentPricing(String item) {
        return null;
    }

}
