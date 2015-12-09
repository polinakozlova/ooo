package ui;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Set;

import domain.*;

/**
 * @author Yannick Crabb�, Polina Kozlova
 */
public class Controller {
	private ProductDB productDB;
	private ProductRepository productRepo;
	private HashMap<Product, Integer> currentSale;

	public Controller() {
		try {
			productDB = new ProductDB();
		} catch (SQLException e) {
		}
		productRepo = productDB.getRepo();
		currentSale = new HashMap<>();
	}

	public void addProductToSale(String code, String quantity) {
		Product product = productDB.getProductById(code);
		if (this.currentSale.get(product) == null)
			this.currentSale.put(product, Integer.parseInt(quantity));
		else
			this.currentSale.put(product, (this.currentSale.get(product) + Integer.parseInt(quantity)));
	}

	public void setProductSaleQuantity(String code, int quantity) {
		Product product = productDB.getProductById(code);
		this.currentSale.put(product, quantity);
	}

	public void removeProductFromSale(String code) {
		Product product = productDB.getProductById(code);
		this.currentSale.remove(product);
	}

	public String getTotalPrice() {
		double price = 0;
		for (Product pr : currentSale.keySet()) {
			price += pr.getPrice() * currentSale.get(pr);
		}
		return String.valueOf(price);
	}

	public double getProductPrice(String id) {
		return productRepo.getProductById(id).getPrice();
	}

	public String getProductDescription(String id) {
		return productRepo.getProductById(id).getDescription();
	}

	public String getIDByDescription(String description) {
		for (Product pr : currentSale.keySet()) {
			if (pr.getDescription().equals(description))
				return pr.getId();
		}
		return null;
	}

	public Set<Product> getProducts() {
		return this.currentSale.keySet();
	}

	public int getProductQuantity(Product product) {
		return this.currentSale.get(product);
	}

	public boolean checkValidPromoCode(String code) {
		//TODO
		return false;		
	}
}