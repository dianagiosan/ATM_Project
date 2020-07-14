import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class ATM {
	private static ATM ATMinstance = new ATM();
	private List<billEntry> availableBills = new ArrayList<>();
	
	private ATM() {
		availableBills.add(new billEntry(100, 50));
		availableBills.add(new billEntry(50, 50));
		availableBills.add(new billEntry(10, 100));
		availableBills.add(new billEntry(5, 100));
		availableBills.add(new billEntry(1, 100));
		
	}
	
	public static ATM getInstance() {
		return ATMinstance;
	}
	
	public List<billEntry> getAvailableBills() {
		return availableBills;
	}
	
	public ATMOutput splitIntoBills(int cashToWithdraw) {
		List <billEntry> returnBills = new ArrayList<>();
		returnBills.add(new billEntry(0, 0));
		return new ATMOutput(returnBills, "Not okay");
	}
	
	
}