import java.util.Scanner;

public class ATMDriver {
	public static void main(String[] args) {
		ATM thisATM = new ATM();
		while (true) {
			Scanner scanner = new Scanner(System.in);
			int cashToWithdraw = scanner.nextInt();
			System.out.println(thisATM.splitIntoBills(cashToWithdraw));
		}
		
	}
}
