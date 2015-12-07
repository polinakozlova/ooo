package ui;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Product;
import domain.ProductDB;
import domain.ProductRepository;

/**
 * @author Yannick Crabbé, Polina Kozlova
 */
public class Controller {
	
	ProductDB productDB;
	ProductRepository productRepo;
	
	ArrayList<Product> producten;
	
	public Controller(){
		try {
			productDB = new ProductDB();
		} catch (SQLException e) {
		}
		productRepo = productDB.getRepo();
		producten = new ArrayList<>();
	}
	
	public void addProductToSale(String code, String quantity){
		int quantityInt = Integer.parseInt(quantity);
		Product product = productDB.getProductById(code);
		if(product == null){
			//error message
		}
		for(int i = 0; i < quantityInt; i++){
			producten.add(product);
		}
	}
	
	public String getTotalPrice(){
		double price = 0;
		for(Product pr : producten){
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
}
