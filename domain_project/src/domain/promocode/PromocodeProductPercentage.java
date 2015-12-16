package domain.promocode;

import domain.Sale;
import domain.product.Product;

/**
 * @author Polina Kozlova
 */

public class PromocodeProductPercentage extends Promocode {
	private double percentage;
	private Product product;

	public PromocodeProductPercentage(int id, double percentage, Product product) {
		super(PromocodeType.PRODUCTAMOUNT, id);
		makePromocode(percentage, product);
	}

	public void setProduct(Product product) {
		this.product = product;
		System.out.println(product.getId());
	}

	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}

	public double getPercentage() {
		return percentage;
	}

	public Product getProduct() {
		return this.product;
	}

	@Override
	public double getReducedPrice(Sale sale) {
		if (sale.contains(getProduct())) {
			return sale.getPrice()
					- getProduct().getPrice() / 100 * getPercentage() * sale.getProductQuantity(getProduct());
		}
		return sale.getPrice();
	}

	public void makePromocode(double percentage, Product product) {

		setProduct(product);
		setPercentage(percentage);
	}
}
