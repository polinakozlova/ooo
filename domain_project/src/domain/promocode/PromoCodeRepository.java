package domain.promocode;

import java.util.ArrayList;

/**
 * @author Polina Kozlova
 */
public class PromoCodeRepository {
	private ArrayList<Promocode> promoCodes = new ArrayList<Promocode>();
	
	public PromoCodeRepository () {
	}
	
	public void add(Promocode promoCode){
		promoCodes.add(promoCode);
	}
	
	public Promocode getPromoCodeById(int id){
		for(Promocode pc : promoCodes){
			if(pc.getId() == id){
				return pc;
			}
		}
		return null;
	}
}
