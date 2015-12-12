package domain;

import java.util.Set;

public class Facade {
	private Sale sale;
	
	public Facade() {
		this.sale = new Sale();
	}

	public void addProductToSale(String id, String quantity) {
		sale.addProductToSale(id, quantity);	
	}
	
	public double getTotalPrice() {
		return sale.getTotalPrice();
	}
	
	public Set<Product> getCurrentSale() {
		return sale.getCurrentSale();
	}
	
	public void setProductSaleQuantity(String id, int quantity) {
		sale.setProductSaleQuantity(id, quantity);
	}
	
	public void removeProductFromSale(String id) {
		sale.removeProductFromSale(id);
	}
	
	public double getProductPrice(String id) {
		return sale.getProductPrice(id);
	}
	
	public String getProductDescription(String id) {
		return sale.getProductDescription(id);
	}
	
	public String getIDByDescription(String description) {
		return sale.getIDByDescription(description);
	}
	
	public int getProductQuantity(Product product) {
		return sale.getProductQuantity(product);
	}
	
	public boolean checkValidPromoCode(String promocode) {
		return sale.checkValidPromoCode(promocode);		
	}
	
	public void emptyCurrentSale() {
		sale.emptyCurrentSale();
	}

	public void adjustPriceAfterPromo() {
		// TODO Auto-generated method stub
		
	}
	
	public String getProductDescription(Product product){
		return product.getDescription();
	}
	
	public double getProductPrice(Product product) {
		return product.getPrice();
	}	
}