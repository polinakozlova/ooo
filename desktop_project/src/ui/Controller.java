package ui;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import domain.Product;
import domain.ProductDB;
import domain.ProductRepository;

/**
 * @author Yannick Crabb�, Polina Kozlova
 */
public class Controller {

	ProductDB productDB;
	ProductRepository productRepo;

	HashMap<Product,Integer> producten;

	public Controller() {
		try {
			productDB = new ProductDB();
		} catch (SQLException e) {
		}
		productRepo = productDB.getRepo();
		producten = new HashMap<>();
	}

	public void addProductToSale(String code, String quantity) {
		int quantityInt = Integer.parseInt(quantity);
		Product product = productDB.getProductById(code);
		if (product == null) {
			// error message
		}
		this.addProduct(product, quantityInt);
	}

	public String getTotalPrice() {
		double price = 0;
		for (Product pr : producten.keySet()) {
			price += pr.getPrice();
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
		for (Product pr : producten.keySet()) {
			if (pr.getDescription().equals(description))
				return pr.getId();
		}
		return null;
	}
	
	public void addProduct(Product product, int quantity) {
		this.producten.put(product, quantity);
	}
}