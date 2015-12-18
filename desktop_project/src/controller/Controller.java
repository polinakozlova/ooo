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
	
	public double getDayDiscount() {
		return facade.getDayDiscount();
	}
	
	public void addProductToSale(String id, String quantity) {
		facade.addProductToSale(id, quantity);
	}

	public void emptyCurrentSale() {
		facade.emptyCurrentSale();
	}

	public String getIDByDescription(String description) {
		return facade.getIDByDescription(description);
	}

	public String getProductDescription(String id) {
		return facade.getProductDescription(id);
	}

	public double getProductPrice(String id) {
		return facade.getProductPrice(id);
	}

	public double getReducedPrice(String promocode){
		return facade.getReducedPrice(promocode);
	}

	public double getTotalPrice() {
		return facade.getTotalPrice();
	}

	public void registerObserver(Observer o) {
		facade.registerObserver(o);
	}

	public void removeProductFromSale(String id) {
		facade.removeProductFromSale(id);
	}
	
	public void setProductSaleQuantity(String id, int quantity) {
		facade.setProductSaleQuantity(id, quantity);
	}
	
	public void setSalePrice(double newPrice) {
		facade.setSalePrice(newPrice);
	}
	
	public Object[][] updateSaleTable(Object[][] table) {
		return facade.updateSaleTable(table);
	}
}