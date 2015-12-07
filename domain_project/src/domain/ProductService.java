package domain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Polina Kozlova
 */
public class ProductService {
	private ProductRepository productRepository = new ProductRepository();

	public ArrayList<Product> producten;
	
	public ProductService(){
	}
	
	public Product getProduct(String productId) {
		return getProductRepository().get(Integer.parseInt(productId));
	}

	public List<Product> getProducts() {
		return getProductRepository().getAll();
	}

	public void addProduct(Product product) {
		getProductRepository().add(product);
	}

	public void deleteProduct(String id) {
		getProductRepository().delete(id);
	}

	private ProductRepository getProductRepository() {
		return productRepository;
	}
}
