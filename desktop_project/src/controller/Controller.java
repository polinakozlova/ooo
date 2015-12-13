package controller;

import java.util.Set;

import domain.Facade;
import domain.product.Product;

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

	public Set<Product> getCurrentSale() {
		return facade.getCurrentSale();
	}

	public int getProductQuantity(Product product) {
		return facade.getProductQuantity(product);
	}

	public boolean checkValidPromoCode(String promocode) {
		return facade.checkValidPromoCode(promocode);	
	}

	public void emptyCurrentSale() {
		facade.emptyCurrentSale();
	}

	public void adjustPriceAfterPromo() {
		facade.adjustPriceAfterPromo();	
	}
	
	public String getProductDescription(Product product){
		return facade.getProductDescription(product);
	}
	
	public double getProductPrice(Product product) {
		return facade.getProductPrice(product);
	}
	
	public Object[][] updateSaleTable(Object[][] tableData) {
		return facade.updateSaleTable(tableData);
	}
}