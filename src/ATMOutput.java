import java.util.List;
import java.util.Objects;

public class ATMOutput { //class that models the output the ATM provides the user with,
	//consisting of the bills and their respective amount, as well as a specific success/error message
	List<billEntry> bills;
	String message;
	
	public ATMOutput(List<billEntry> bills, String message) {
		this.bills = bills;
		this.message = message;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ATMOutput atmOutput = (ATMOutput) o;
		return bills.equals(atmOutput.bills) &&
			message.equals(atmOutput.message);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(bills, message);
	}
	
	public String toString() {
		return message + ". Here is your money: " + bills;
	}
}
