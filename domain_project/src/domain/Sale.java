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
import domain.promocode.PromocodeDB;
import domain.promocode.PromocodeRepository;
import observer.Observable;
import observer.Observer;
import states.*;

/**

 * @author Yannick Crabb�, Polina Kozlova
 */
public class Sale implements Observable {
	private double price;
	private ProductDB productDB;
	private PromocodeDB promoCodeDB;
	private ProductRepository productRepo;
	private PromocodeRepository pcr;
	private HashMap<Product, Integer> currentSale;
	private ArrayList<Observer> observers;
	private State state;
	private State pendingState;
	private State cancelledState;
	private State paidState;
	private Promocode promocode;

	public Sale() throws NoSuchAlgorithmException, UnsupportedEncodingException {
		this.currentSale = new HashMap<Product, Integer>();
		try {
			this.productDB = new ProductDB();
			this.promoCodeDB = new PromocodeDB(productDB);
		} catch (SQLException e) {
		}
		this.productRepo = productDB.getRepo();
		this.pcr = promoCodeDB.getRepo();
		this.pendingState = new PendingState(this);
		this.cancelledState = new CancelledState(this);
		this.paidState = new PaidState(this);
		this.setState(pendingState);
		this.price = 0;
		this.observers = new ArrayList<Observer>();
		this.setPromocode(promocode);
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
	
	public void setPrice(double price){
		this.price = price;
		this.notifyObservers(String.valueOf(price));
	}
	
	public void addProductToSale(String code, String quantity) {
		Product product = productDB.getProductById(code);
		if (this.currentSale.get(product) == null)
			this.currentSale.put(product, Integer.parseInt(quantity));
		else
			this.currentSale.put(product, (this.currentSale.get(product) + Integer.parseInt(quantity)));
		this.setPrice((this.getPrice() + product.getPrice() * Integer.parseInt(quantity)));
	}

	public void setProductSaleQuantity(String id, int quantity) {
		Product product = productDB.getProductById(id);
		int oldQuantity = this.getProductQuantity(product);
		this.currentSale.put(product, quantity);
		this.setPrice(this.getPrice() + ((quantity - oldQuantity) * product.getPrice()));
	}

	public void removeProductFromSale(String code) {
		Product product = productDB.getProductById(code);
		int quantity = this.getProductQuantity(product);
		double price = product.getPrice();
		this.currentSale.remove(product);
		this.setPrice(this.getPrice() - (quantity * price));
	}

	public double getPrice() {
		return this.price;
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
		return amountPaid - this.getPrice();
	}
	
	public void emptyCurrentSale() {
		this.setPrice(0);
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

	public double getReducedPrice(String promocode) {
		Promocode pc = this.getPromocodeById(promocode);
		double reducedPrice = pc.getReducedPrice(this);
		return reducedPrice;
	}
	
	public Promocode getPromocodeById(String promocodeString){
		Promocode pc = pcr.getPromoCodeById(Integer.parseInt(promocodeString));
		return pc;
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
		while (i < 10) {
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
		message = String.valueOf(this.getPrice());
		for (Observer ops : observers) 
			ops.setText(message);
	}
}