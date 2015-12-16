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
		makePromocode(amount, product);
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
<<<<<<< HEAD
			return sale.getPrice() - getAmount() * sale.getProductQuantity(product);
=======
			System.out.println("in if");
			double newPrice = sale.getPrice() - getAmount() * sale.getProductQuantity(product);
			System.out.println(newPrice);
			return newPrice;
>>>>>>> 8c1d10dea5df1e877ef32b9df4beeec59f790b23
		}
		return sale.getPrice();
	}
	

<<<<<<< HEAD
	public void makePromocode(double amount, Product product) {
=======
	public void makePromocode() {
>>>>>>> 8c1d10dea5df1e877ef32b9df4beeec59f790b23
		setAmount(amount);
		setProduct(product);
	}
}