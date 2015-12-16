package domain.promocode;

import domain.product.Product;
/**
 * @author Polina Kozlova
 */

public class PromocodeFactory {
	public static Promocode makePromocode(PromocodeType type, int id, double amount, Product product, double percentage, double saleAmountNeeded) throws Exception{
		Promocode promocode = null;
		switch(type){
		case PRODUCTAMOUNT:
			promocode = new PromocodeProductAmount(id, saleAmountNeeded, product);
			break;
		case PRODUCTPERCENTAGE:
			promocode = new PromocodeProductPercentage(id, percentage, product);
			break;
		case SALEAMOUNT:
			promocode = new PromocodeSaleAmount(id, saleAmountNeeded, amount);
			break;
		case SALEPERCENTAGE:
			promocode = new PromocodeSalePercentage(id, percentage);
			break;
		default:
			throw new IllegalArgumentException("Unknown type");
		}
		return promocode;
	}
}
