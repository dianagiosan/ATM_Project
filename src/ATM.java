import java.util.ArrayList;
import java.util.List;

public class ATM { //main class that models the ATM functionality
	
	boolean critical100MessageSent = false; //in order to avoid sending the warning mail twice for the same event
	boolean warning100MessageSent = false;
	boolean warning50MessageSent = false;
	private List<billEntry> availableBills = new ArrayList<>(); //the list of available bills in the ATM
	
	public ATM() { //initialize the available bills every time an ATM object is created
		availableBills.add(new billEntry(100, 50));
		availableBills.add(new billEntry(50, 50));
		availableBills.add(new billEntry(10, 100));
		availableBills.add(new billEntry(5, 100));
		availableBills.add(new billEntry(1, 100));
		
	}
	
	public void changeInitialATMConfiguration(List<billEntry> newConfiguration) { //for testing purposes only
		availableBills.clear();
		availableBills.addAll(newConfiguration);
		
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
		String message;
		boolean OKtoContinue = false;
		for (billEntry entry : availableBills) {
			if (entry.getBillAmount() != 0) { //first, check to see if the ATM is not empty
				OKtoContinue = true;
				break;
			}
		}
		if (!OKtoContinue)
			try {
				throw new NotEnoughCashLeftException(); //throw exception, there's no more cash in the ATM
			} catch (NotEnoughCashLeftException e) {
				e.printStackTrace();
				message = "Transaction denied";
				returnBills.add(new billEntry(0, 0)); //no money outputted
				return new ATMOutput(returnBills, message);
			}
		List<billEntry> availableBillsCopy = new ArrayList<>(); //create a working copy of the ATM bills
		for (billEntry entry : availableBills) {
			billEntry entryCopy = new billEntry(entry.getBillValue(), entry.getBillAmount());
			availableBillsCopy.add(entryCopy);
		}
		
		while (cashToWithdraw > 0 && currentBillIndex < availableBillsCopy.size()) { //we still have to withdraw cash
			currentBillCounter = 0;
			
			while (availableBillsCopy.get(currentBillIndex).getBillAmount() > 0 &&
				cashToWithdraw >= availableBillsCopy.get(currentBillIndex).getBillValue()) { //try to include one more of the current bill
				cashToWithdraw -= availableBillsCopy.get(currentBillIndex).getBillValue();//decrease the goal withdrawal sum
				availableBillsCopy.get(currentBillIndex).decreaseAmount(); //lower the amount of the current bill in the list
				currentBillCounter++;//we've added one more of the current bill
			}
			
			if (currentBillCounter > 0) //we have to add a new bill to the output, with it's corresponding amount
				returnBills.add(new billEntry(availableBills.get(currentBillIndex).getBillValue(), currentBillCounter));
			currentBillIndex++;
			
		}
		
		
		if (cashToWithdraw > 0) { //if the cash could not be withdrawn, it's because the bill configuration in the ATM doesn't allow it
			message = "Transaction denied";
			returnBills.clear();
			returnBills.add(new billEntry(0, 0)); //no money outputted
			try {
				throw new TransactionNotPossibleException(); //throw exception
			} catch (TransactionNotPossibleException e) {
				e.printStackTrace();
			}
		} else {
			message = "Transaction approved"; //if the transaction was successful, update the new bill configuration in the ATM
			availableBills.clear();
			availableBills.addAll(availableBillsCopy);
			//check to see if we have to send a warning mail
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
			}
		}
		return new ATMOutput(returnBills, message);
	}
	
	
}