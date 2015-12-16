package domain.promocode;

import domain.Sale;
/**
 * @author Polina Kozlova
 */

public abstract class Promocode {
	private int id;
	private PromocodeType type;
	
	public Promocode(PromocodeType type, int id){
		setId(id);
		setType(type);
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public PromocodeType getType(){
		return this.type;
	}
	
	public void setType(PromocodeType type){
		this.type = type;
	}
	
	public abstract double getReducedPrice(Sale sale);
	

}