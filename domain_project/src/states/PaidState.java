package states;

import domain.Sale;

/**
 * @author Yannick Crabbé
 */
public class PaidState implements State {
	@SuppressWarnings("unused")
	private Sale sale;
	
	public PaidState(Sale sale) {
		this.sale = sale;
	}
	
	public void delete(String code) {
	}

	public void add(String code, String quantity) {
	}

	public void cancel() {
	}

	public void pay(double amountPaid) {
	}
}