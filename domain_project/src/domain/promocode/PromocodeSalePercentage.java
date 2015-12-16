package domain.promocode;

import domain.Sale;
/**
 * @author Polina Kozlova
 */

public class PromocodeSalePercentage extends Promocode {
	private double percentage;

	public PromocodeSalePercentage(int id, double percentage) {
		super(PromocodeType.SALEPERCENTAGE, id);
		makePromocode(percentage);
	}

	private void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public double getPercentage() {
		return this.percentage;
	}

	@Override
	public double getReducedPrice(Sale sale) {
		return sale.getPrice() - sale.getPrice() * (this.getPercentage() / 100);
	}

	public void makePromocode(double percentage) {
		this.setPercentage(percentage);
	}

}