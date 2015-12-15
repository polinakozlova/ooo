package domain.promocode;

import domain.Sale;
import domain.product.Product;

/**
 * @author Polina Kozlova
 */

public class PromocodeProductPercentage extends Promocode {
	private int percentage;
	private Product product;

	public PromocodeProductPercentage(int id, int percentage, Product product) {
		super(PromocodeType.PRODUCTAMOUNT, id);
		makePromocode();
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public int getPercentage() {
		return percentage;
	}

	public Product getProduct() {
		return this.product;
	}

	@Override
	public double getReducedPrice(Sale sale) {
		if (sale.contains(getProduct())) {
			return sale.getTotalPrice()
					- getProduct().getPrice() / 100 * getPercentage() * sale.getProductQuantity(getProduct());
		}
		return sale.getTotalPrice();
	}

	@Override
	public void makePromocode() {
		setProduct(product);
		setPercentage(percentage);
	}
}
