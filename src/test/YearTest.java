package test;

import org.jfree.data.time.TimePeriodFormatException;
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


    @Test public void equals_SHOULD_returnTrue_WHEN_receiveSameYear() {
        Year year1 = new Year(2025);
        Year year2 = new Year(2025);
        assertTrue(year1.equals(year2));
    }

    @Test public void equals_SHOULD_returnFalse_WHEN_receiveDifferentYear() {
        Year year1 = new Year(2025);
        Year year2 = new Year(2026);
        assertFalse(year1.equals(year2));
    }


    @Test  public void getFirstMillisecond_SHOULD_returnFirstMillisecond_WHEN_receiveSameCalenderYear() {
        // arrange data
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.JANUARY, 1, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        long firstMillisecond = calendar.getTimeInMillis();

        Year year = new Year(2025);


        // act(to be tested)
        long yearFMS = year.getFirstMillisecond(calendar); // receive the same calender year


        // assertion
        assertEquals(firstMillisecond, yearFMS);
    }

    @Test public void getFirstMillisecond_SHOULD_throwNullPointerException_WHEN_receivesNoCalenderYear() {
        Year year = new Year(2025);

        assertThrows(NullPointerException.class, () -> {
            year.getFirstMillisecond((Calendar) null); // idk why it wanted type casting
        });
    }


    @Test public void getLastMillisecond_SHOULD_returnLastMillisecond_WHEN_receiveSameCalenderYear() {
        // arrange
        Calendar calendar = Calendar.getInstance();
        calendar.set(2025, Calendar.DECEMBER, 31, 23, 59, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        long calenderLMS = calendar.getTimeInMillis();
        Year year = new Year(2025);

        // act
        long yearLMS = year.getLastMillisecond(calendar);

        // assertion
        assertEquals(calenderLMS, yearLMS);
    }


    @Test public void getSerialIndex_SHOULD_returnIndex_WHEN_receiveSameYearValue() {
        int yearVal = 2025;

        Year year = new Year(2025);

        assertEquals(yearVal, year.getSerialIndex());
    }


    @Test public void hashCode_SHOULD_returnSameHash_WHEN_receiveSameYearValue() {
        Year year1 = new Year(2025);
        Year year2 = new Year(2025);
        assertEquals(year1.hashCode(), year2.hashCode());
    }

    @Test public void hashCode_SHOULD_returnDifferentHash_WHEN_receiveDifferentYearValue() {
        Year year1 = new Year(2025);
        Year year2 = new Year(2026);
        assertNotEquals(year1.hashCode(), year2.hashCode());
    }


    @Test
    public void next_SHOULD_returnNextYear_WHEN_receivesAnyYearValue() {
        Year expectedNextYear = new Year(2026);

        Year actualNextYear = (Year) new Year(2025).next();

        assertEquals(expectedNextYear, actualNextYear);
    }

    @Test
    public void parseYear_SHOULD_returnYearObject_WHEN_receivesYearValue() {
        //parseYear takes a year string and returns its year obj

        // arrange
        String yearValue = "2025";
        Year expectedYearObj = new Year(2025);
        // act
        Year parsedYear = Year.parseYear(yearValue);

        // assertion
        assertEquals(expectedYearObj, parsedYear);
    }

    @Test
    public void parseYear_SHOULD_returnTimePeriodException_WHEN_receivesInvalidYearValue() {
        // arrange
        String invalidYearValue = "anything";

        // assert
        assertThrows(TimePeriodFormatException.class, () -> {
            // act
            Year.parseYear(invalidYearValue);
        });
    }


    @Test
    public void parseYear_SHOULD_throwNullPointerException_WHEN_receivesNullYearValue() {
        assertThrows(NullPointerException.class, () -> {
            Year.parseYear(null);
        });
    }
}
