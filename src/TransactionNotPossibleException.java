public class TransactionNotPossibleException extends Exception { //the exception that is thrown when there is not enough
	//cash left in the ATM for the transaction
	public TransactionNotPossibleException() {
		super("There are not enough bills in the ATM for the desired transaction. Try another transaction.");
	}
}
