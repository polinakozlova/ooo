package ui;

import java.sql.SQLException;

import domain.Product;
import domain.ProductDB;
import domain.ProductRepository;



public class Controller {
	
	ProductDB productDB;
	ProductRepository productRepo;
	
	public Controller(){
		try {
			productDB = new ProductDB();
		} catch (SQLException e) {
		}
		productRepo = productDB.getRepo();
	}
	
	public void addProductToSale(String code, String quantity, String price){
		int quantityInt = Integer.parseInt(quantity);
		double priceDouble = Double.parseDouble(price);
		for(int i = 0; i < quantityInt; i++){
			Product product = new Product();
			product.setId(code);
			product.setPrice(priceDouble);
			productRepo.add(product);
			try {
				productDB.add(product);
			} catch (ClassNotFoundException e) {
			}
		}
		
		
	}
}
