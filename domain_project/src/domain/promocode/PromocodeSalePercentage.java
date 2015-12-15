package domain.promocode;

import domain.Sale;
/**
 * @author Polina Kozlova
 */

public class PromocodeSalePercentage extends Promocode {
	private int percentage;

	public PromocodeSalePercentage(int id, int percentage) {
		super(PromocodeType.SALEPERCENTAGE, id);
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

	@Override
	public void makePromocode() {
		setPercentage(percentage);
	}
}