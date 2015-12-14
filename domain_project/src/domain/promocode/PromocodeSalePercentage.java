package domain.promocode;

import domain.Sale;

public class PromocodeSalePercentage extends Promocode {
	private int percentage;

	public PromocodeSalePercentage(int id, int percentage) {
		super(id);
		setPercentage(percentage);
	}

	private void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getPercentage() {
		return this.percentage;
	}

	@Override
	public double getReducedPrice(Sale sale) {
		return sale.getTotalPrice() - sale.getTotalPrice() / 100 * getPercentage();
	}
}