/**
 * 
 */
package product;

import java.util.ArrayList;
import java.util.Set;

import dao.ProductDAO;
import partner.Partner;

/**
 * @author julianareider
 *
 */
public class ProductManager {
	
	private static ProductDAO dao = new ProductDAO();
	
	/**
	 * 
	 */
	public ProductManager() {
		// TODO Auto-generated constructor stub
	}
	
	//TODO method to searchForProduct 
	

	/**
	 * View all products
	 */
	public Set<Product> getAllProducts(){
		return dao.getAllProducts();
	}
	
	/**
	 * Search for product
	 */
	public Product getProduct(String id) {
		return dao.getProduct(id);
	}
	
	public ArrayList<Product> searchForProduct(String search) {
		return dao.performProductSearch(search);
	}
	public Product addProduct(String name, String description, int price, int productOwnerPartnerID) {
		
		Product product = dao.addProduct(name, description, price, productOwnerPartnerID);
		
		return product;
	}

	public void updateProduct(int id, int price) {
		dao.updateProduct(id, price);
	}

	public void deleteProduct(String id) {
		dao.deleteProduct(id);
	}

}
