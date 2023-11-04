package logmonitoring.model;

import main.java.logmonitoring.model.Report;
import org.junit.Test;

import java.text.ParseException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class ReportTest extends Report {

    public String addHyphens(String inputStr){
        return super.addHyphens(inputStr);
    }

    public String convertTo24HourFormat(String inputDateTime) throws ParseException {
        return super.convertTo24HourFormat(inputDateTime);
    }

    @Test
    public void addHyphensTest(){
        var reportTest = new ReportTest();

        // Test case 1: Input with less than or equal to 3 characters
        assertEquals("123", reportTest.addHyphens("123"));

        // Test case 2: Input with more than 3 characters
        assertEquals("123-456", reportTest.addHyphens("123456"));

        // Test case 3: Input with 6 characters
        assertEquals("123-456-789", reportTest.addHyphens("123456789"));

        // Test case 4: Input with 7 characters
        assertEquals("123-456-7890", reportTest.addHyphens("1234567890"));

        // Test case 5: Input with 10 characters
        assertEquals("123-456-789-0AB", reportTest.addHyphens("1234567890AB"));

        // Test case 6: Input with 1 character
        assertEquals("A", reportTest.addHyphens("A"));

        // Test case 7: Input with 2 characters
        assertEquals("AB", reportTest.addHyphens("AB"));
    }

    @Test
    public void testConvertTo24HourFormat() throws ParseException {
        var reportTest = new ReportTest();

        // Test case 1: Valid input in 12-hour format
        String inputDateTime1 = "01/15/2023 01:30:00 PM UTC+0000";
        String expectedOutput1 = "2023-01-15 13:30:00";
        assertEquals(expectedOutput1, reportTest.convertTo24HourFormat(inputDateTime1));

        // Test case 2: Valid input in 12-hour format with a different time zone
        String inputDateTime2 = "01/15/2023 01:30:00 PM UTC+0300";
        String expectedOutput2 = "2023-01-15 13:30:00";
        assertEquals(expectedOutput2, reportTest.convertTo24HourFormat(inputDateTime2));

        // Test case 3: Valid input in 12-hour format with a different date format
        String inputDateTime3 = "12/01/2023 01:30:00 PM UTC+0000";
        String expectedOutput3 = "2023-12-01 13:30:00";
        assertEquals(expectedOutput3, reportTest.convertTo24HourFormat(inputDateTime3));

        // Test case 4: Invalid input (should throw ParseException)
        String invalidInput = "invalidDateTime";
        assertThrows(ParseException.class, () -> reportTest.convertTo24HourFormat(invalidInput));
    }
}
