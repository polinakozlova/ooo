package states;
import domain.*;

public class PendingState implements State {
	private Sale sale;
	
	public PendingState(Sale sale) {
		this.sale = sale;
	}

	public void delete(String code) {
		sale.removeProductFromSale(code);	
	}

	public void add(String code, String quantity) {
		sale.addProductToSale(code, quantity);
	}

	public void cancel() {
		sale.emptyCurrentSale();
	}

	public void pay(double amountPaid) {
		sale.paySale(amountPaid);
	}
}