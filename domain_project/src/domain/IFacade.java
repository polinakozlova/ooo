package domain;

import java.util.Set;
import domain.product.Product;
import observer.Observer;

/**
 * @author Yannick Crabbé
 */
public interface IFacade {
	public void addProductToSale(String id, String quantity);
	
	public void emptyCurrentSale();
	
	public Set<Product> getCurrentSale();
	
	public String getIDByDescription(String description);
	
	public String getProductDescription(Product product);
	
	public String getProductDescription(String id);
	
	public double getProductPrice(Product product);
	
	public double getProductPrice(String id);
	
	public int getProductQuantity(Product product);
	
	public double getReducedPrice(String promocode);
	
	public Sale getSale();
	
	public double getTotalPrice();

	public void registerObserver(Observer o);
	
	public void removeProductFromSale(String id);
	
	public void setProductSaleQuantity(String id, int quantity);
	
	public Object[][] updateSaleTable(Object[][] table);
	
}
