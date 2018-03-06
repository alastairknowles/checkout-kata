package epos.checkout;

import com.google.common.collect.ImmutableList;
import epos.ApplicationConfiguration;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
public class CheckoutServiceITest {

    @Autowired
    private CheckoutService checkoutService;

    @Test
    public void shouldCalculateTotal() {
        CheckoutReconciliation checkoutReconciliation = checkoutService.checkout(
                ImmutableList.of(
                        new CheckoutInput().setItem("A"),
                        new CheckoutInput().setItem("B"),
                        new CheckoutInput().setItem("C"),
                        new CheckoutInput().setItem("C"),
                        new CheckoutInput().setItem("C")));

        Assert.assertEquals(625, checkoutReconciliation.getTotal().longValue());
        Assert.assertEquals(50, checkoutReconciliation.getDiscount().longValue());
        Assert.assertEquals(3, checkoutReconciliation.getOutputs().size());
    }

}
