
		This project simulates an ATM functionality. Running the ATMDriver, the user can insert the sum of cash they would like 
	to withdraw, and the program simply divides the sum into as few bills as possible, keeping track of the available bills
	that are left in the ATM. If the user tries to input anything other than an integer, a message reminds them to enter a valid
	input.
		If the transaction is not possible, an exception is thrown, either due to the fact that the current configuration of bills
	in the atm no longer allows for that sum to be split up, or because the ATM has completely run out of cash. In both cases, a user-friendly
	message is displayed to inform the user. 
		If the transaction is succesful, a success message is displayed, followed by the bill configuration that the user receives.
		
