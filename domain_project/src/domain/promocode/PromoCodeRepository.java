package domain.promocode;

import java.util.ArrayList;

/**
 * @author Polina Kozlova
 */
public class PromoCodeRepository {
	private ArrayList<PromoCode> promoCodes = new ArrayList<PromoCode>();
	
	public PromoCodeRepository () {
	}
	
	public void add(PromoCode promoCode){
		promoCodes.add(promoCode);
	}
	
	public PromoCode getPromoCodeById(int id){
		for(PromoCode pc : promoCodes){
			if(pc.getId() == id){
				return pc;
			}
		}
		return null;
	}
}
