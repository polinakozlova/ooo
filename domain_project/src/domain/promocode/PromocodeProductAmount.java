package domain.promocode;

import domain.Sale;
import domain.product.Product;
/**
 * @author Polina Kozlova
 */
public class PromocodeProductAmount extends Promocode {
	private double amount;
	private Product product;

	public PromocodeProductAmount(int id, double amount, Product product) {
		super(PromocodeType.PRODUCTAMOUNT, id);
		makePromocode();
	}

	private void setAmount(double amount) {
		this.amount = amount;
	}

	public double getAmount() {
		return this.amount;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Product getProduct() {
		return this.product;
	}

	@Override
	public double getReducedPrice(Sale sale) {
		if (sale.contains(product)) {
			return sale.getTotalPrice() - getAmount() * sale.getProductQuantity(product);
		}
		return sale.getTotalPrice();
	}

	@Override
	public void makePromocode() {
		setAmount(amount);
		setProduct(product);
	}
}