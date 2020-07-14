import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class ATMTest {
	
	
	@Test
	public void getAvailableBills() {
		ATM ATMTest = new ATM();
		ArrayList<billEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new billEntry(100, 50));
		expectedBills.add(new billEntry(50, 50));
		expectedBills.add(new billEntry(10, 100));
		expectedBills.add(new billEntry(5, 100));
		expectedBills.add(new billEntry(1, 100));
		assertArrayEquals(ATMTest.getAvailableBills().toArray(), expectedBills.toArray());
	}
	
	@Test
	public void splitIntoBills_151() {
		ATM ATMTest = new ATM();
		List<billEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new billEntry(100, 1));
		expectedBills.add(new billEntry(50, 1));
		expectedBills.add(new billEntry(1, 1));
		ATMOutput actual = ATMTest.splitIntoBills(151);
		ATMOutput expected = new ATMOutput(expectedBills, "OK");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_103() {
		ATM ATMTest = new ATM();
		List<billEntry> expectedBills = new ArrayList<>();
		ATMOutput actual = ATMTest.splitIntoBills(103);
		expectedBills.add(new billEntry(100, 1));
		expectedBills.add(new billEntry(1, 3));
		ATMOutput expected = new ATMOutput(expectedBills, "OK");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_50() {
		ATM ATMTest = new ATM();
		List<billEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new billEntry(50, 1));
		ATMOutput actual = ATMTest.splitIntoBills(50);
		ATMOutput expected = new ATMOutput(expectedBills, "OK");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_7510() {
		ATM ATMTest = new ATM();
		List<billEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new billEntry(100, 50));
		expectedBills.add(new billEntry(50, 50));
		expectedBills.add(new billEntry(10, 1));
		ATMOutput actual = ATMTest.splitIntoBills(7510);
		ATMOutput expected = new ATMOutput(expectedBills, "OK");
		assertEquals(expected, actual);
		
	}
	
	@Test
	public void splitIntoBills_60() {
		ATM ATMTest = new ATM();
		List<billEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new billEntry(50, 1));
		expectedBills.add(new billEntry(10, 1));
		ATMOutput actual = ATMTest.splitIntoBills(60);
		ATMOutput expected = new ATMOutput(expectedBills, "OK");
		assertEquals(expected, actual);
		
	}
	
	@Test(expected = NotEnoughCashLeftException.class)
	public void splitIntoBillsOverflow() {
		ATM ATMTest = new ATM();
		List<billEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new billEntry(0, 0));
		ATMOutput actual = ATMTest.splitIntoBills(10000);
		ATMOutput expected = new ATMOutput(expectedBills, "Not okay");
		assertEquals(expected, actual);
		
		
	}
	
	@Test
	public void splitIntoBillsRepeatedly() {
		ATM ATMTest = new ATM();
		List<billEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new billEntry(100, 50));
		expectedBills.add(new billEntry(50, 50));
		ATMOutput actual = ATMTest.splitIntoBills(7500);
		ATMOutput expected = new ATMOutput(expectedBills, "OK");
		assertEquals(expected, actual);
		expectedBills.clear();
		expectedBills.add(new billEntry(0, 0));
		actual = ATMTest.splitIntoBills(7500);
		expected = new ATMOutput(expectedBills, "Not okay");
		assertEquals(expected, actual);
		
		
	}
}