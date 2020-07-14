import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertArrayEquals;

public class ATMTest {
	
	@Test
	public void getAvailableBills() {
		ATM ATMTest = ATM.getInstance();
		ArrayList<billEntry> expectedBills = new ArrayList<>();
		expectedBills.add(new billEntry(100, 50));
		expectedBills.add(new billEntry(50, 50));
		expectedBills.add(new billEntry(10, 100));
		expectedBills.add(new billEntry(5, 100));
		expectedBills.add(new billEntry(1, 100));
		assertArrayEquals(ATMTest.getAvailableBills().toArray(), expectedBills.toArray());
	}
}