package epos.checkout;

import com.google.common.collect.ImmutableList;
import epos.pricing.Pricing;
import epos.pricing.PricingService;
import org.assertj.core.api.Assertions;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceUTest {

    @Mock
    private PricingService pricingService;

    private CheckoutServiceImpl checkoutService;

    @Before
    public void setUp() {
        when(pricingService.getCurrentPricing("A")).thenReturn(new Pricing().setItem("A").setPrice(100L));
        when(pricingService.getCurrentPricing("B")).thenReturn(new Pricing().setItem("B").setPrice(100L)
                .setOffer(new Pricing.Offer().setMultiple(3L).setPrice(200L)));
        checkoutService = new CheckoutServiceImpl(pricingService);
    }

    @After
    public void tearDown() {
        reset(pricingService);
    }

    @Test
    public void shouldCalculateTotalUtilisingDefaultPricing() {
        CheckoutReconciliation reconciliation = checkoutService.checkout(
                ImmutableList.of(
                        new CheckoutInput().setItem("A"),
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("A"),
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("B")));

        Assertions.assertThat(reconciliation.getOutputs()).containsOnly(
                new CheckoutOutput().setItem("A").setPrice(100L).setQuantity(2L).setDiscount(0L).setTotal(200L),
                new CheckoutOutput().setItem("B").setPrice(100L).setQuantity(3L).setDiscount(100L).setTotal(200L));

        assertEquals(400L, reconciliation.getTotal().longValue());
        assertEquals(100L, reconciliation.getDiscount().longValue());

        verify(pricingService, times(1)).getCurrentPricing("A");
        verify(pricingService, times(1)).getCurrentPricing("B");
    }

    @Test
    public void shouldCalculateTotalOverridingDefaultPricing() {
        CheckoutReconciliation reconciliation = checkoutService.checkout(
                ImmutableList.of(
                        new CheckoutInput().setItem("A"),
                        new CheckoutInput().setItem("B")),
                ImmutableList.of(
                        new Pricing().setItem("A").setPrice(50L),
                        new Pricing().setItem("B").setPrice(25L)));

        Assertions.assertThat(reconciliation.getOutputs()).containsOnly(
                new CheckoutOutput().setItem("A").setPrice(50L).setQuantity(1L).setDiscount(0L).setTotal(50L),
                new CheckoutOutput().setItem("B").setPrice(25L).setQuantity(1L).setDiscount(0L).setTotal(25L));

        assertEquals(75L, reconciliation.getTotal().longValue());
        assertEquals(0L, reconciliation.getDiscount().longValue());

        verify(pricingService, times(0)).getCurrentPricing("A");
        verify(pricingService, times(0)).getCurrentPricing("B");
    }

    @Test
    public void shouldCalculateTotalOverridingDefaultPricingAndOffer() {
        CheckoutReconciliation reconciliation = checkoutService.checkout(
                ImmutableList.of(
                        new CheckoutInput().setItem("A"),
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("B")),
                ImmutableList.of(
                        new Pricing().setItem("A").setPrice(50L),
                        new Pricing().setItem("B").setPrice(25L)
                                .setOffer(new Pricing.Offer().setMultiple(4L).setPrice(10L))));

        Assertions.assertThat(reconciliation.getOutputs()).containsOnly(
                new CheckoutOutput().setItem("A").setPrice(50L).setQuantity(1L).setDiscount(0L).setTotal(50L),
                new CheckoutOutput().setItem("B").setPrice(25L).setQuantity(5L).setDiscount(90L).setTotal(35L));

        assertEquals(85L, reconciliation.getTotal().longValue());
        assertEquals(90L, reconciliation.getDiscount().longValue());

        verify(pricingService, times(0)).getCurrentPricing("A");
        verify(pricingService, times(0)).getCurrentPricing("B");
    }

    @Test
    public void shouldCalculateTotalRejectingUnknownItems() {
        CheckoutReconciliation reconciliation = checkoutService.checkout(
                ImmutableList.of(
                        new CheckoutInput().setItem("A"),
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("C")));

        assertThat(reconciliation.getOutputs()).containsOnly(
                new CheckoutOutput().setItem("A").setPrice(100L).setQuantity(1L).setDiscount(0L).setTotal(100L),
                new CheckoutOutput().setItem("B").setPrice(100L).setQuantity(1L).setDiscount(0L).setTotal(100L));

        assertEquals(200L, reconciliation.getTotal().longValue());
        assertEquals(0L, reconciliation.getDiscount().longValue());

        verify(pricingService, times(1)).getCurrentPricing("A");
        verify(pricingService, times(1)).getCurrentPricing("B");
        verify(pricingService, times(1)).getCurrentPricing("C");
    }

    @Test
    public void shouldOnlyApplyOfferPricingToOfferMultiplesAndNormalPricingToRemainder() {
        CheckoutReconciliation reconciliation = checkoutService.checkout(
                ImmutableList.of(
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("B")));

        assertThat(reconciliation.getOutputs()).containsOnly(
                new CheckoutOutput().setItem("B").setPrice(100L).setQuantity(4L).setDiscount(100L).setTotal(300L));

        assertEquals(300L, reconciliation.getTotal().longValue());
        assertEquals(100L, reconciliation.getDiscount().longValue());

        verify(pricingService, times(1)).getCurrentPricing("B");
    }

    @Test
    public void shouldNotApplyOfferPricingWhenOfferMultipleNotMet() {
        CheckoutReconciliation reconciliation = checkoutService.checkout(
                ImmutableList.of(
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("B")));

        assertThat(reconciliation.getOutputs()).containsOnly(
                new CheckoutOutput().setItem("B").setPrice(100L).setQuantity(2L).setDiscount(0L).setTotal(200L));

        assertEquals(200L, reconciliation.getTotal().longValue());
        assertEquals(0L, reconciliation.getDiscount().longValue());

        verify(pricingService, times(1)).getCurrentPricing("B");
    }

}
