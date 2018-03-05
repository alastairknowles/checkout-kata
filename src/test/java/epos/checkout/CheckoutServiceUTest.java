package epos.checkout;

import epos.pricing.PricingService;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutServiceUTest {

    @Mock
    private PricingService pricingService;

    private CheckoutServiceImpl checkoutService;

    @Before
    public void setUp() {

    }

    @After
    public void tearDown() {

    }

    @Test
    public void shouldCalculateTotalUtilisingDefaultPricing() {
        Assert.fail();
    }

    @Test
    public void shouldCalculateTotalOverridingDefaultPricing() {
        Assert.fail();
    }

    @Test
    public void shouldCalculateTotalRejectingUnknownItems() {
        Assert.fail();
    }

    @Test
    public void shouldOnlyApplyOfferPricingToOfferMultiples() {
        Assert.fail();
    }

    @Test
    public void shouldNotApplyOfferPricingWhenOfferQuantityNotMet() {
        Assert.fail();
    }

}
