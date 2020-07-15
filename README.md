# ATM Project

This project simulates an ATM functionality. Running the ATMDriver, the user can insert the sum of cash they would like 
to withdraw, and the program simply divides the sum into as few bills as possible, keeping track of the available bills
that are left in the ATM.

![](https://github.com/dianagiosan/ATM_Project/blob/master/readme_images/1.png)

If the user tries to input anything other than an integer, a message reminds them to enter a valid
input.

![](https://github.com/dianagiosan/ATM_Project/blob/master/readme_images/7.png)

If the transaction is not possible, an exception is thrown, either due to the fact that the current configuration of bills
in the atm no longer allows for that sum to be split up, or because the ATM has completely run out of cash. In both cases, a user-friendly
message is displayed to inform the user. 


![](https://github.com/dianagiosan/ATM_Project/blob/master/readme_images/3.png)


![](https://github.com/dianagiosan/ATM_Project/blob/master/readme_images/5.png)


If the transaction is succesful, a success message is displayed, followed by the bill configuration that the user receives.


![](https://github.com/dianagiosan/ATM_Project/blob/master/readme_images/2.png)

Also, when the amount of 100 or 50 bills is critically low, an email (mock email) is sent to inform the authority in question about
the situation


![](https://github.com/dianagiosan/ATM_Project/blob/master/readme_images/4.png)
		
