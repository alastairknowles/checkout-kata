package epos.pricing;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PricingServiceUTest {

    @Mock
    private PricingRepository pricingRepository;

    private PricingServiceImpl pricingService;

    @Before
    public void setUp() {
        Pricing pricing = new Pricing().setItem("A").setPrice(100L);
        when(pricingRepository.getCurrentPricing("A")).thenReturn(pricing);
        pricingService = new PricingServiceImpl(pricingRepository);
    }

    @After
    public void tearDown() {
        reset(pricingRepository);
    }

    @Test
    public void shouldRetrieveDefaultPricing() {
        Pricing pricing = pricingService.getCurrentPricing("A");
        Assert.assertNotNull(pricing);
        Assert.assertEquals("A", pricing.getItem());
        Assert.assertEquals(100L, pricing.getPrice().longValue());
        verify(pricingRepository, times(1)).getCurrentPricing("A");
    }

}
