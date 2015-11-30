package domain;

public class Product {

	private String id;
	private String description;
	private double price;
	
	public Product(){
		
	}

	public Product(String id, String description, double price) {
		setId(id);
		setDescription(description);
		setPrice(price);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	//TEST

}
