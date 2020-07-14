import java.util.ArrayList;
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
		boolean critical100MessageSent = false;
		boolean warning100MessageSent = false;
		boolean warning50MessageSent = false;
		while (cashToWithdraw > 0 && currentBillIndex < availableBills.size()) {
			currentBillCounter = 0;
			
			while (availableBills.get(currentBillIndex).getBillAmount() > 0 &&
				cashToWithdraw >= availableBills.get(currentBillIndex).getBillValue()) {
				cashToWithdraw -= availableBills.get(currentBillIndex).getBillValue();
				availableBills.get(currentBillIndex).decreaseAmount();
				if (availableBills.get(0).getBillAmount() < 5 && !critical100MessageSent) {
					MockMail.send("Number of 100 bills is CRITICAL.");
					critical100MessageSent = true;
				} else if (availableBills.get(0).getBillAmount() < 10 && !warning100MessageSent) {
					MockMail.send("Warning. Number of 100 bills under 20%.");
					warning100MessageSent = true;
				}
				if (availableBills.get(1).getBillAmount() <= 7 && !warning50MessageSent) {
					
					MockMail.send("Warning. Number of 50 bills is critical.");
					warning50MessageSent = true;
				}
				currentBillCounter++;
			}
			
			if (currentBillCounter > 0)
				returnBills.add(new billEntry(availableBills.get(currentBillIndex).getBillValue(), currentBillCounter));
			currentBillIndex++;
			
		}
		String message;
		if (cashToWithdraw > 0) {
			message = "Not okay";
			returnBills.clear();
			returnBills.add(new billEntry(0, 0));
		} else message = "OK";
		return new ATMOutput(returnBills, message);
	}
	
	
}