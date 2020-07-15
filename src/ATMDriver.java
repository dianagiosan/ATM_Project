import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMDriver {
	public static void main(String[] args) {
		ATM thisATM = new ATM();
		while (true) {
			int cashToWithdraw = 0;
			Scanner scanner = new Scanner(System.in);
			try {
				cashToWithdraw = scanner.nextInt();
				System.out.println(thisATM.splitIntoBills(cashToWithdraw));
			} catch (InputMismatchException e) {
				System.out.println("Insert a sum of cash to withdraw");
			}
			
		}
		
	}
}
