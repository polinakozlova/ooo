package domain.promocode;

import domain.Sale;

public abstract class Promocode {
	private int id;
	
	public Promocode(int id){
		this.id = id;
		
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public abstract double getReducedPrice(Sale sale);
}