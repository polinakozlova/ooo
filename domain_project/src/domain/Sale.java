package domain;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import domain.product.Product;
import domain.product.ProductDB;
import domain.product.ProductRepository;
import domain.promocode.Promocode;
import domain.promocode.PromoCodeDB;
import domain.promocode.PromoCodeRepository;
import observer.Observable;
import observer.Observer;
import states.*;

public class Sale implements Observable {
	private ProductDB productDB;
	private PromoCodeDB promoCodeDB;
	private ProductRepository productRepo;
	private PromoCodeRepository pcr;
	private HashMap<Product, Integer> currentSale;
	private ArrayList<Observer> observers;
	private State state;
	private State pendingState;
	private State cancelledState;
	private State paidState;
	private double price;
	private Promocode promocode;

	public Sale() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this.currentSale = new HashMap<Product, Integer>();
		try {
			this.productDB = new ProductDB();
			this.promoCodeDB = new PromoCodeDB(productDB);
		} catch (SQLException e) {
		}
		this.productRepo = productDB.getRepo();
		this.pcr = promoCodeDB.getRepo();
		this.pendingState = new PendingState(this);
		this.cancelledState = new CancelledState(this);
		this.paidState = new PaidState(this);
		this.setState(pendingState);
		this.price = this.getTotalPrice();
		this.observers = new ArrayList<Observer>();
		setPromocode(promocode);
	}
	
	private void setPromocode(Promocode pc){
		this.promocode = pc;
	}
	
	public Promocode getPromocode(){
		return this.promocode;
	}
	
	public boolean contains(Product product) {
		return currentSale.containsKey(product);
	}
	
	public State getState() {
		return this.state;
	}
	
	public void setState(State state) {
		this.state = state;
	}
	
	public void addProductToSale(String code, String quantity) {
		Product product = productDB.getProductById(code);
		if (this.currentSale.get(product) == null)
			this.currentSale.put(product, Integer.parseInt(quantity));
		else
			this.currentSale.put(product, (this.currentSale.get(product) + Integer.parseInt(quantity)));
		this.notifyObservers(String.valueOf(this.getTotalPrice()));
	}

	public void setProductSaleQuantity(String code, int quantity) {
		Product product = productDB.getProductById(code);
		this.currentSale.put(product, quantity);
	}

	public void removeProductFromSale(String code) {
		Product product = productDB.getProductById(code);
		this.currentSale.remove(product);
		this.notifyObservers(String.valueOf(this.getTotalPrice()));
	}

	public double getTotalPrice() {
		double price = 0;
		for (Product pr : currentSale.keySet()) {
			price += pr.getPrice() * currentSale.get(pr);
		}
		return price;
	}
	
	public String getIDByDescription(String description) {
		for (Product pr : currentSale.keySet()) {
			if (pr.getDescription().equals(description))
				return pr.getId();
		}
		return null;
	}
	
	public double paySale(double amountPaid) {
		this.setState(paidState);
		return amountPaid - this.getTotalPrice();
	}
	
	public void emptyCurrentSale() {
		this.setState(cancelledState);	
	}
	
	public double getProductPrice(String id) {
		return productRepo.getProductById(id).getPrice();
	}

	public String getProductDescription(String id) {
		return productRepo.getProductById(id).getDescription();
	}

	public Set<Product> getCurrentSale() {
		return this.currentSale.keySet();
	}

	public int getProductQuantity(Product product) {
		return this.currentSale.get(product);
	}


	public double getReducedPrice(){
		if(this.promocode != null){
			return promocode.getReducedPrice(this);
		}
		return this.getTotalPrice();
	}
	
	public Object[][] updateSaleTable(Object[][] tableData) {
		int i = 0;
		for (Product pr : this.getCurrentSale()) {
			tableData[i][0] = pr.getDescription();
			tableData[i][1] = this.getProductQuantity(pr);
			tableData[i][2] = pr.getPrice();
			tableData[i][3] = pr.getPrice() * this.getProductQuantity(pr);
			i++;
		}
		while (i < 420) {
			tableData[i][0] = "";
			tableData[i][1] = "";
			tableData[i][2] = "";
			tableData[i][3] = "";
			i++;
		}
		return tableData;
	}

	public void registerObserver(Observer o) {
		observers.add(o);
	}

	public void removeObserver(Observer o) {
		observers.remove(o);
	}

	public void notifyObservers(String message) {
		//message = String.valueOf(this.getTotalPrice());
		for (Observer ops : observers) 
			ops.setText(message);
	}
}