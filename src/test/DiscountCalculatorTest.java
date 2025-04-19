package test;

import JFree.DiscountCalculator;
import org.jfree.data.time.Week;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

public class DiscountCalculatorTest {

    @Test
    public void testIsTheSpecialWeekWhenFalse() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.FEBRUARY, 22); // Not in week 26
        Date date = calendar.getTime();
        Week week = new Week(date);

        DiscountCalculator discountCalculator = new DiscountCalculator(week);
        assertFalse(discountCalculator.isTheSpecialWeek());
    }

    @Test
    public void testIsTheSpecialWeekWhenTrue() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JUNE, 24); // 24 of June , 2025 is in week 26
        Date date = calendar.getTime();
        Week week = new Week(date);

        DiscountCalculator discountCalculator = new DiscountCalculator(week);
        assertTrue(discountCalculator.isTheSpecialWeek());
    }

    @Test
    public void testGetDiscountPercentageWhenWeekIsEven() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 7); // Week 2 "even"
        Date date = calendar.getTime();
        Week week = new Week(date);

        DiscountCalculator discountCalculator = new DiscountCalculator(week);
        assertEquals(7, discountCalculator.getDiscountPercentage());
    }

    @Test
    public void testGetDiscountPercentageWhenWeekIsOdd() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1); // Week 1 "odd"
        Date date = calendar.getTime();
        Week week = new Week(date);

        DiscountCalculator discountCalculator = new DiscountCalculator(week);
        assertEquals(5, discountCalculator.getDiscountPercentage());
    }
}
