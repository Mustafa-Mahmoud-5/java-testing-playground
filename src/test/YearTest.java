package test;

import org.jfree.data.time.Year;
import org.junit.Test;

import java.util.Calendar;

// get all assertions
import static org.junit.Assert.*;

public class YearTest {
    Year year;

    private void arrange() {
        year = new Year();
    }
    @Test
    public void testYearDefaultCtor() {
        arrange();
        assertEquals(2025, year.getYear());
    }


    @Test
    public void compareTo_SHOULD_returnNegative() {
        Year year1 = new Year(2024);
        Year year2 = new Year(2025);
        assertEquals(-1, year1.compareTo(year2));
    }


    @Test public void compareTo_SHOULD_returnPositive() {
        Year year1 = new Year(2026);
        Year year2 = new Year(2025);
        assertEquals(1, year1.compareTo(year2));
    }
}
