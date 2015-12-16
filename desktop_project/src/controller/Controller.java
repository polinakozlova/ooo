package controller;

import domain.Facade;
import observer.Observer;

/**
 * @author Yannick Crabb�, Polina Kozlova
 */
public class Controller {
	private Facade facade;

	public Controller() {
		this.facade = new Facade();
	}

	public void addProductToSale(String id, String quantity) {
		facade.addProductToSale(id, quantity);
	}

	public void setProductSaleQuantity(String id, int quantity) {
		facade.setProductSaleQuantity(id, quantity);
	}

	public void removeProductFromSale(String id) {
		facade.removeProductFromSale(id);
	}

	public double getTotalPrice() {
		return facade.getTotalPrice();
	}

	public double getProductPrice(String id) {
		return facade.getProductPrice(id);
	}

	public String getProductDescription(String id) {
		return facade.getProductDescription(id);
	}

	public String getIDByDescription(String description) {
		return facade.getIDByDescription(description);
	}

	public void emptyCurrentSale() {
		facade.emptyCurrentSale();
	}

	public void adjustPriceAfterPromo() {
		facade.adjustPriceAfterPromo();	
	}
	
	public Object[][] updateSaleTable(Object[][] table) {
		return facade.updateSaleTable(table);
	}
	
	public void registerObserver(Observer o) {
		facade.registerObserver(o);
	}
}