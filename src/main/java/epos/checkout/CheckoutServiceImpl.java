package epos.checkout;

import epos.pricing.Pricing;
import epos.pricing.PricingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CheckoutServiceImpl implements CheckoutService {

    private final PricingService pricingService;

    @Autowired
    public CheckoutServiceImpl(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public CheckoutReconciliation checkout(List<CheckoutInput> items) {
        // Maintain a local copy of pricings, so we don't change the price half way through
        Map<String, Pricing> pricingMap = new HashMap<>();
        Map<String, CheckoutOutput> outputMap = new LinkedHashMap<>();
        items.forEach(item -> {
            String itemCode = item.getItem();
            Pricing pricing = getPricingAndMemoizeIfNecessary(itemCode, item, pricingMap);

            CheckoutOutput output = outputMap.get(itemCode);
            if (output == null) {
                Long price = pricing.getPrice();
                output = new CheckoutOutput().setItem(itemCode).setPrice(price).setQuantity(1L).setTotal(price);
                outputMap.put(itemCode, output);
            } else {
                Long quantity = output.getQuantity();
                output.setQuantity(quantity + 1);
            }

            recomputeCheckoutOutput(output, pricing);
        });

        // We could compute and reconcile in a single loop but it's not necessary for performance - keep it readable
        return reconcile(outputMap);
    }

    private Pricing getPricingAndMemoizeIfNecessary(String itemCode, CheckoutInput item,
                                                    Map<String, Pricing> pricingMap) {
        Pricing pricing = item.getPricingOverride();
        if (pricing == null) {
            pricing = pricingMap.get(itemCode);
            if (pricing == null) {
                pricing = pricingService.getCurrentPricing(itemCode);
                pricingMap.put(itemCode, pricing);
            }
        }

        return pricing;
    }

    private void recomputeCheckoutOutput(CheckoutOutput output, Pricing pricingMap) {
        Long price = output.getPrice();
        Long quantity = output.getQuantity();

        Long naiveTotal = price * quantity;
        Pricing.Offer offer = pricingMap.getOffer();
        if (offer == null) {
            // No offer - use naive total
            output.setTotal(naiveTotal);
            return;
        }

        Long offerMultiple = offer.getMultiple();
        if (quantity < offerMultiple) {
            // Insufficient quantity to quality for offer - use naive total
            output.setTotal(naiveTotal);
            return;
        }

        // Qualify for offer = compute adjusted total
        Long offerPrice = offer.getPrice();
        Long offerInstances = Math.floorDiv(quantity, offerMultiple);

        Long priceInstances = quantity - (offerInstances * offerMultiple);
        Long total = (offerPrice * offerInstances) + (price + priceInstances);
        Long discount = naiveTotal - total;

        output.setTotal(total);
        output.setDiscount(discount);
    }

    private CheckoutReconciliation reconcile(Map<String, CheckoutOutput> outputMap) {
        Collection<CheckoutOutput> outputs = outputMap.values();
        CheckoutReconciliation reconciliation = new CheckoutReconciliation();
        reconciliation.getOutputs().addAll(outputs);

        // This would have been more efficient as a single for loop - but to show knowledge of the 'streaming api'
        reconciliation.setTotal(
                outputs.stream()
                        .map(CheckoutOutput::getTotal)
                        .mapToLong(Long::longValue)
                        .sum());

        reconciliation.setDiscount(
                outputs.stream()
                        .map(CheckoutOutput::getDiscount)
                        .mapToLong(Long::longValue)
                        .sum());

        return reconciliation;
    }

}
