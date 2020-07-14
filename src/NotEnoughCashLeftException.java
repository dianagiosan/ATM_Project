public class NotEnoughCashLeftException extends Exception { //the exception that is thrown when there is not enough
	//cash left in the ATM for the transaction
	public NotEnoughCashLeftException() {
		super("There is no cash left in the ATM.");
	}
}
