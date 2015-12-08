package ui;

import java.sql.SQLException;
import java.util.HashMap;

import javax.swing.JOptionPane;

import domain.*;

/**
 * @author Yannick Crabb�, Polina Kozlova
 */
public class Controller {
	ProductDB productDB;
	ProductRepository productRepo;
	HashMap<Product, Integer> currentSale;

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
	
	public void removeProductFromSale(String code) {
		this.currentSale.remove(code);
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
}