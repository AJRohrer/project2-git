/**
 * 
 */
package order;

import java.util.ArrayList;

import dao.OrderDAO;
import product.Product;

/**
 * @author julianareider
 *
 */
public class OrderManager {
	
	private static OrderDAO dao = new OrderDAO();
	//current orders?
	//completed orders?

	public OrderManager() {
		// TODO Auto-generated constructor stub
	}
	
	//TODO: make proper functionality for order Manager.
	//	+ getAcknowledgementOfShippment
	
	
	public ArrayList<Order> getAllOrders(){
		return dao.getAllOrders();
	}
	
	public Order getOrder(int id) {
		return dao.getOrder(id);
	}
	
	public Order addOrder(int customerID, ArrayList<Integer> productIDs, String CreditCardNo) {
	
		Order order = dao.addOrder(customerID, productIDs, CreditCardNo);
		
		return order;
	}

	public void updateOrder(String id) {
		dao.updateOrder(id);
	}
	
	public void updateOrderStatus(String orderid, String status) {
		dao.updateOrderStatus(Integer.parseInt(orderid), status);
	}

	public void deleteOrder(String id) {
		dao.deleteOrder(id);
	}

	public String getOrderStatus(int orderID) {
		return dao.getOrderStatus(orderID);
	}
	
	/**
	 * @return current Order status. Shipped if shipped, other status if failed to ship it
	 */
	public String shipOrder(int orderID) {
		boolean isShipped = false;
		//update status 
		isShipped = dao.updateOrderStatus(orderID,"shipped");
		//return the current status of the order
		return dao.getOrderStatus(orderID);
	}
	
	/**
	 * @return current Order status. Shipped if shipped, other status if failed to ship it
	 */
	public String cancelOrder(int orderID) {
		boolean isCanceled = false;
		//cancel by updating status
		isCanceled = dao.updateOrderStatus(orderID,"canceled");
		//return the current status of the order
		return dao.getOrderStatus(orderID);
	}

}
