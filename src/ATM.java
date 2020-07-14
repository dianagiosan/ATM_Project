import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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
		availableBills.sort((billEntry1, billEntry2) -> billEntry2.getBillValue() - billEntry1.getBillValue());
		int currentBillIndex = 0;
		int currentBillCounter;
		List<billEntry> returnBills = new ArrayList<>();
		while (cashToWithdraw > 0) {
			currentBillCounter = 0;
			while (availableBills.get(currentBillIndex).getBillAmount() > 0 &&
			cashToWithdraw >= availableBills.get(currentBillIndex).getBillValue()){
				cashToWithdraw -= availableBills.get(currentBillIndex).getBillValue();
				availableBills.get(currentBillIndex).decreaseAmount();
				currentBillCounter++;
				
			}
			if(currentBillCounter > 0)
			returnBills.add(new billEntry(availableBills.get(currentBillIndex).getBillValue(), currentBillCounter));
			currentBillIndex++;
			
		}
		String message = "OK";
		return new ATMOutput(returnBills, message);
	}
	
	
}