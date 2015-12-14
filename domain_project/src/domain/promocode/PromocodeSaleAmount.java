package domain.promocode;

import domain.Sale;

public class PromocodeSaleAmount extends Promocode {
	
	private double saleAmountNeeded;
	private double amount;
	
	public PromocodeSaleAmount(int id, double saleAmountNeeded, double amount) {
		super(id);
		setSaleAmountNeeded(saleAmountNeeded);
		setAmount(amount);
	}

	private void setAmount(double amount) {
		this.amount = amount;
	}
	
	public double getAmount(){
		return this.amount;
	}
	
	private void setSaleAmountNeeded(double saleAmountNeeded){
		this.saleAmountNeeded = saleAmountNeeded;
	}

	public double getSaleAmountNeeded(){
		return this.saleAmountNeeded;
	}
	
	@Override
	public double getReducedPrice(Sale sale) {
		if(sale.getTotalPrice() >= getSaleAmountNeeded()){
			return sale.getTotalPrice() - getAmount();
		}
		return sale.getTotalPrice();
	}
	


}
