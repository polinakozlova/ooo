package states;

import domain.Sale;

/**
 * @author Yannick Crabbé
 */
public class PaidState implements State {
	private Sale sale;
	
	public PaidState(Sale sale) {
		this.sale = sale;
	}
	
	public void delete(String code) {
		// TODO Auto-generated method stub
	}

	public void add(String code, String quantity) {

		// TODO Auto-generated method stub
		
	}

	public void cancel() {
		// TODO Auto-generated method stub
		
	}

	public void pay(double amountPaid) {
		// TODO Auto-generated method stub
		
	}
}