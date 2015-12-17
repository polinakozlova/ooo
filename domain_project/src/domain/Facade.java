package domain;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Set;

import domain.product.Product;
import domain.promocode.Promocode;
import observer.Observer;

/**
 * @author Yannick Crabbé
 */
public class Facade implements IFacade {
	private Sale sale;
	
	public Facade() {
		try {
			this.sale = new Sale();
		} catch (NoSuchAlgorithmException e) {
		} catch (UnsupportedEncodingException e) {
		}
	}
	
	public void addProductToSale(String id, String quantity) {
		sale.addProductToSale(id, quantity);	
	}

	public void emptyCurrentSale() {
		sale.emptyCurrentSale();
	}
	
	public Set<Product> getCurrentSale() {
		return sale.getCurrentSale();
	}
	
	public String getIDByDescription(String description) {
		return sale.getIDByDescription(description);
	}
	
	public String getProductDescription(Product product){
		return product.getDescription();
	}
	
	public String getProductDescription(String id) {
		return sale.getProductDescription(id);
	}
	
	public double getProductPrice(Product product) {
		return product.getPrice();
	}
	
	public double getProductPrice(String id) {
		return sale.getProductPrice(id);
	}
	
	public int getProductQuantity(Product product) {
		return sale.getProductQuantity(product);
	}
	
	public Promocode getPromocodeById(String promocodeString) {
		return sale.getPromocodeById(promocodeString);
	}
	
	public double getReducedPrice(String promocode){
		return sale.getReducedPrice(promocode);
	}
	
	public Sale getSale() {
		return this.sale;
	}
	
	public double getTotalPrice() {
		return sale.getPrice();
	}
	
	public void registerObserver(Observer o) {
		sale.registerObserver(o);
	}	
	
	public void removeProductFromSale(String id) {
		sale.removeProductFromSale(id);
	}
	
	public void setProductSaleQuantity(String id, int quantity) {
		sale.setProductSaleQuantity(id, quantity);
	}

	public void setSalePrice(double newPrice) {
		sale.setPrice(newPrice);		
	}

	public Object[][] updateSaleTable(Object[][] table) {
		return sale.updateSaleTable(table);
	}
}