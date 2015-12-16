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
		System.out.println(product.getId());
	}

	public Product getProduct() {
		return this.product;
	}

	@Override
	public double getReducedPrice(Sale sale) {
		if (sale.contains(product)) {
			System.out.println("in if");
			double newPrice = sale.getPrice() - getAmount() * sale.getProductQuantity(product);
			System.out.println(newPrice);
			return newPrice;
		}
		return sale.getPrice();
	}

	public void makePromocode() {
		setAmount(amount);
		setProduct(product);
	}
}