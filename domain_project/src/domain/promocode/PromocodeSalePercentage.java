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
<<<<<<< HEAD
=======
		System.out.println(sale.getPrice());
		System.out.println(this.getPercentage());
		System.out.println(sale.getPrice() - sale.getPrice() * (getPercentage() / 100));
>>>>>>> 8c1d10dea5df1e877ef32b9df4beeec59f790b23
		return sale.getPrice() - sale.getPrice() * (this.getPercentage() / 100);
	}

	public void makePromocode(double percentage) {
<<<<<<< HEAD
=======
		System.out.println("percentage: " + percentage);
>>>>>>> 8c1d10dea5df1e877ef32b9df4beeec59f790b23
		this.setPercentage(percentage);
	}

}