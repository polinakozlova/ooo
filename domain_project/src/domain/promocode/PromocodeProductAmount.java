package domain.promocode;

import domain.Sale;
import domain.product.Product;

public class PromocodeProductAmount extends Promocode {
	private double amount;
	private Product product;

	public PromocodeProductAmount(int id, double amount, Product product) {
		super(id);
		setAmount(amount);
		setProduct(product);
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
}