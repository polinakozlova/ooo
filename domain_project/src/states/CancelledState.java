package states;

import domain.Sale;

public class CancelledState implements State {
	private Sale sale;

	public CancelledState(Sale sale) {
		this.sale = sale;
	}

	public void delete(String code) {

	}

	public void add(String code, String quantity) {
		sale.addProductToSale(code, quantity);	
	}

	public void cancel() {
	}

	public void pay(double amountPaid) {
	}
}