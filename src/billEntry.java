import java.util.Objects;

public class billEntry {
	private int billValue;
	private int billAmount;
	
	public billEntry(int billValue, int billAmount) {
		this.billAmount = billAmount;
		this.billValue = billValue;
	}
	
	public int getBillAmount() {
		return billAmount;
	}
	
	public int getBillValue() {
		return billValue;
	}
	
	public void decreaseAmount() {
		billAmount--;
	}
	
	public String toString() {
		return "value " + billValue + ";amount " + billAmount + " ";
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		billEntry billEntry = (billEntry) o;
		return billValue == billEntry.billValue &&
			billAmount == billEntry.billAmount;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(billValue, billAmount);
	}
}

