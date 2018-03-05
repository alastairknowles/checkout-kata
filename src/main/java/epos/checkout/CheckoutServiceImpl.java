package epos.checkout;

import epos.pricing.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PricingService pricingService;

    @Autowired
    public CheckoutServiceImpl(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public List<Checkout> checkout(List<String> items) {
        return null;
    }

}
