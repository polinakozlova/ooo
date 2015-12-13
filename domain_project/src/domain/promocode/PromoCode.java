package domain.promocode;

public class PromoCode {
	int id;
	int type;
	int percentage;
	double amount;
	double saleAmountNeeded;
	
	public PromoCode(int id, int type, int percentage, double amount, double saleAmountNeeded){
		this.id = id;
		this.type = type;
		this.percentage = percentage;
		this.amount = amount;
		this.saleAmountNeeded = saleAmountNeeded;
		
	}
	public double getSaleAmountNeeded() {
		return saleAmountNeeded;
	}
	public void setSaleAmountNeeded(double saleAmountNeeded) {
		this.saleAmountNeeded = saleAmountNeeded;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}
