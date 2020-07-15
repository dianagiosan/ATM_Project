import java.util.Scanner;

public class ATMDriver {
	public static void main(String[] args) {
		while(true) {
		Scanner scanner = new Scanner(System.in);
		int cashToWithdraw = scanner.nextInt();
		ATM thisATM = new ATM();
		System.out.println(thisATM.splitIntoBills(cashToWithdraw));
	}
	}
}
