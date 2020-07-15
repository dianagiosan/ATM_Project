public class NotEnoughCashLeftException extends Exception {
	public NotEnoughCashLeftException(){
		super("The ATM ran out of cash. Please come back later.");
	}
}
