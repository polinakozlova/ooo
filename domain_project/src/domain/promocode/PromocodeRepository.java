package domain.promocode;

import java.util.ArrayList;

/**
 * @author Polina Kozlova
 */
public class PromocodeRepository {
	private ArrayList<Promocode> promoCodes = new ArrayList<Promocode>();

	public PromocodeRepository() {
	}

	public void add(Promocode promoCode) {
		promoCodes.add(promoCode);
	}

	public Promocode getPromoCodeById(int id) {
		System.out.println("id" + id);
		System.out.println("in getpromobyid pcr");
		for (Promocode pc : promoCodes) {
			System.out.println("in forloop pcr");
			System.out.println(pc.getId());
			if (pc.getId() == id) {
				System.out.println("pc found pcr");
				return pc;
			}
		}
		return null;
	}
}