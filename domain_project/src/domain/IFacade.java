package domain;

import java.util.Set;

import domain.product.Product;
import observer.Observer;

public interface IFacade {
	public void addProductToSale(String id, String quantity);

	public void adjustPriceAfterPromo();
	
	public double getReducedPrice(Sale sale);
	
	public void emptyCurrentSale();
	
	public Set<Product> getCurrentSale();
	
	public String getIDByDescription(String description);
	
	public String getProductDescription(Product product);
	
	public String getProductDescription(String id);
	
	public double getProductPrice(Product product);
	
	public double getProductPrice(String id);
	
	public int getProductQuantity(Product product);
	
	public Sale getSale();

	public double getTotalPrice();
	
	public void registerObserver(Observer o);
	
	public void removeProductFromSale(String id);
	
	public void setProductSaleQuantity(String id, int quantity);
	
	public Object[][] updateSaleTable(Object[][] tableData);
}