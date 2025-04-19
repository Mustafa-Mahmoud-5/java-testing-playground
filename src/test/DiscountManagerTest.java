package test;
import JFree.DiscountManager;
import JFree.IDiscountCalculator;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;
import static org.junit.Assert.*;

public class DiscountManagerTest {

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsFalse() {
        boolean isDiscountsSeason = false;
        double originalPrice = 100.0;
        double expectedPrice = 100.0;

        Mockery context = new Mockery();
        IDiscountCalculator mockedCalculator = context.mock(IDiscountCalculator.class);

        // Expect that no methods will be called when isDiscountsSeason is false
        context.checking(new Expectations() {
            {
                never(mockedCalculator); // as isDiscountsSeason will be false, calculator methods will never be reached
            }
        });

        DiscountManager manager = new DiscountManager(isDiscountsSeason, mockedCalculator);
        double actualPrice = manager.calculatePriceAfterDiscount(originalPrice);
        assertEquals(expectedPrice, actualPrice , 0.001);
        context.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsTrue() {
        boolean isDiscountsSeason = true;
        double originalPrice = 200.0;
        double expectedPrice = 160.0; // 20% discount during special week

        Mockery context = new Mockery();
        IDiscountCalculator mockedCalculator = context.mock(IDiscountCalculator.class);

        context.checking(new Expectations() {{
            oneOf(mockedCalculator).isTheSpecialWeek(); will(returnValue(true));
            never(mockedCalculator).getDiscountPercentage(); // last line should never get executed
        }});

        DiscountManager manager = new DiscountManager(isDiscountsSeason, mockedCalculator);
        double actualPrice = manager.calculatePriceAfterDiscount(originalPrice);
        assertEquals(expectedPrice, actualPrice, 0.001);
        context.assertIsSatisfied();
    }

    @Test
    public void testCalculatePriceWhenDiscountsSeasonIsTrueAndSpecialWeekIsFalse() {
        boolean isDiscountsSeason = true;
        double originalPrice = 150.0;
        int discountPercent = 9; // 9% discount
        double expectedPrice = originalPrice -  (originalPrice * discountPercent / 100);

        Mockery context = new Mockery();
        IDiscountCalculator mockedCalculator = context.mock(IDiscountCalculator.class);

        context.checking(new Expectations() {{
            oneOf(mockedCalculator).isTheSpecialWeek(); will(returnValue(false));
            oneOf(mockedCalculator).getDiscountPercentage(); will(returnValue(discountPercent));
        }});

        DiscountManager manager = new DiscountManager(isDiscountsSeason, mockedCalculator);
        double actualPrice = manager.calculatePriceAfterDiscount(originalPrice);
        assertEquals(expectedPrice, actualPrice, 0.001);
        context.assertIsSatisfied();
    }
}
