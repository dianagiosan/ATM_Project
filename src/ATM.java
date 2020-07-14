import java.util.ArrayList;
import java.util.List;

public class ATM { //main class that models the ATM functionality
	
	private List<billEntry> availableBills = new ArrayList<>(); //the list of available bills in the ATM
	
	public ATM() { //initialize the available bills every time an ATM object is created
		availableBills.add(new billEntry(100, 50));
		availableBills.add(new billEntry(50, 50));
		availableBills.add(new billEntry(10, 100));
		availableBills.add(new billEntry(5, 100));
		availableBills.add(new billEntry(1, 100));
		
	}
	
	
	public List<billEntry> getAvailableBills() {
		return availableBills;
	}
	
	public ATMOutput splitIntoBills(int cashToWithdraw) {
		availableBills.sort((billEntry1, billEntry2) -> billEntry2.getBillValue() - billEntry1.getBillValue());
		//sort the list of available bills descendingly by their value
		int currentBillIndex = 0; //the current bill in the list that we try to include in the output
		int currentBillCounter;//how many bills like the current one were we able to include so far?
		List<billEntry> returnBills = new ArrayList<>();
		boolean critical100MessageSent = false; //in order to avoid sending the warning mail twice for the same event
		boolean warning100MessageSent = false;
		boolean warning50MessageSent = false;
		while (cashToWithdraw > 0 && currentBillIndex < availableBills.size()) { //we still have to withdraw cash
			currentBillCounter = 0;
			
			while (availableBills.get(currentBillIndex).getBillAmount() > 0 &&
				cashToWithdraw >= availableBills.get(currentBillIndex).getBillValue()) { //try to include one more of the current bill
				cashToWithdraw -= availableBills.get(currentBillIndex).getBillValue();//decrease the goal withdrawal sum
				availableBills.get(currentBillIndex).decreaseAmount(); //lower the amount of the current bill in the list
				if (availableBills.get(0).getBillAmount() < 5 && !critical100MessageSent) {
					MockMail.send("Number of 100 bills is CRITICAL.");
					critical100MessageSent = true;
				} else if (availableBills.get(0).getBillAmount() < 10 && !warning100MessageSent) {
					MockMail.send("Warning. Number of 100 bills under 20%.");
					warning100MessageSent = true;
				}
				if (availableBills.get(1).getBillAmount() <= 7 && !warning50MessageSent) {
					
					MockMail.send("Warning. Number of 50 bills is under 15%.");
					warning50MessageSent = true;
				} //checked to see if it is the case to send the warning mail
				currentBillCounter++;//we've added one more of the current bill
			}
			
			if (currentBillCounter > 0) //we have to add a new bill to the output, with it's corresponding amount
				returnBills.add(new billEntry(availableBills.get(currentBillIndex).getBillValue(), currentBillCounter));
			currentBillIndex++;
			
		}
		String message;
		if (cashToWithdraw > 0) { //if the cash could not be withdrawn, it's because there's not enough money left
			message = "Transaction denied";
			returnBills.clear();
			returnBills.add(new billEntry(0, 0)); //no money outputted
			try {
				throw new NotEnoughCashLeftException(); //throw exception
			} catch (NotEnoughCashLeftException e) {
				e.printStackTrace();
			}
		} else message = "Transaction approved";
		return new ATMOutput(returnBills, message);
	}
	
	
}