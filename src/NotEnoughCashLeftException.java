public class NotEnoughCashLeftException extends Exception {
	public NotEnoughCashLeftException() {
		super("There is no cash left in the ATM.");
	}
}
