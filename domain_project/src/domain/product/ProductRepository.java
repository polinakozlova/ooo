package domain.product;

import java.util.ArrayList;

/**
 * @author Polina Kozlova
 */
public class ProductRepository {
	private ArrayList<Product> products = new ArrayList<Product>();
	
	public ProductRepository () {
		/*add(new Product(1, "desc1", 1.1));
		add(new Product(2, "desc2", 8));
		add(new Product(3, "desc3", 92.1));*/
	}
	
	public void updateDesc(Product product, String desc){
		product.setDescription(desc);
	}
	
	public void updatePrice(Product product, double price){
		product.setPrice(price);
	}
	
	public Product get(int productId){
		if(productId == 0){
			throw new IllegalArgumentException("No id given");
		}
		for(Product p : products){
			if(p.getId().equals(productId)){
				return p;
			}
		}
		return null;
	}
	
	public ArrayList<Product> getAll(){
		return products;	
	}

	public void add(Product product){
		if(product == null){
			throw new IllegalArgumentException("No product given");
		}
		products.add(product);
	}
	
	
	public void delete(String productId){
		if(productId == null){
			throw new IllegalArgumentException("No id given");
		}
		Product product = getProductById(productId);
		if(product != null){
			products.remove(product);
		}
	}
	
	public Product getProductById(String id){
		for(Product p : products){
			if(p.getId().equals(id)){
				return p;
			}
		}
		return null;
	}
}
