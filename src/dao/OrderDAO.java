/**
 * 
 */
package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import customer.CreditCard;
import order.Order;
import partner.Partner;
import product.Product;
import dao.PartnerDAO;

/**
 * @author julianareider
 *
 */
public class OrderDAO {
	
	private ProductDAO productDAO;

	/**
	 * 
	 */
	public OrderDAO() {
		productDAO = new ProductDAO();
	}

	public ArrayList<Order> getAllOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	
	/** Note to partner: this method is considered complete */
	public String getOrderStatus(int id) {
		String orderStatus = "";
		
		Connection connection = DBConnect.getDatabaseConnection();
		
		try {
			Statement selectStatement = connection.createStatement();
			
			String selectQuery = "SELECT * from Orders where OrderID='" + id +"'";
			ResultSet resultSet = selectStatement.executeQuery(selectQuery);
			resultSet.next();
			
			orderStatus = resultSet.getString("OrderStatus");
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		return orderStatus;
	}
	
	/** Note to partner: I have NOT completed this method */ // - Completed
	public Order getOrder(int id) {		
		Connection conn = DBConnect.getDatabaseConnection();

		Order ord = new Order();
		try {
			Statement select = conn.createStatement();
			
			String query = "SELECT * FROM Orders WHERE OrderID=" + "'" + id + "'";  
			ResultSet rs = select.executeQuery(query);
			rs.next();

			ord.setId(rs.getString("OrderID"));
			ord.setOrderStatus("orderStatus");
			ord.setCustomerID("CustomerID");
			
			
			return ord;
			
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
		return null;
		
		
	}
	
	
	/** Note to partner: this method is considered complete */
	public Order addOrder(int customerID, ArrayList<Integer> productsIDs, String ccNo) {
		Integer resultKey = -1;
		Connection connection = DBConnect.getDatabaseConnection();
		String status = "ordered";

		//add order to orders table
		try {
			Statement insertStatement = connection.createStatement();
			
			String insertQuery = "INSERT INTO Orders VALUES (0,'"+status+"','" + customerID + "', '" + ccNo + "')";
			insertStatement.executeUpdate(insertQuery, Statement.RETURN_GENERATED_KEYS);
				
			//Grab the generated key//get orderID
			ResultSet rs = insertStatement.getGeneratedKeys();
	        if (rs.next()){
	            resultKey = rs.getInt(1);
	        }
	        rs.close();
	        
	        //add order and Products to product table -- for each product ID
	        for (Integer pID : productsIDs) {
				
				insertQuery = "INSERT INTO OrderList (OrderID,ProductID)"
						+ "VALUES('" + resultKey + "', '" + pID + "')";
				insertStatement.executeUpdate(insertQuery);
	        }
			
	        insertStatement.close();
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		//Create new Order with appropriate attributes
		Order order = new Order();
		order.setId(Integer.toString(resultKey));
		order.setOrderStatus(status);
		order.setCreditCardNo(ccNo);
		
		//for each product id, get product object from DB and add to new order object
		for (Integer pID : productsIDs) {
			order.addProduct(productDAO.getProduct(Integer.toString(pID)));
        }
		
		//return that new order object
		return order;
	}

	
	public void updateOrder(String id) {
		// TODO Auto-generated method stub
		
	}

	public void deleteOrder(String id) {
		// TODO Auto-generated method stub
		
	}

	
	/** Note to partner: this method is considered complete */
	public boolean updateOrderStatus(int orderID, String newStatus) {
		boolean isShipped = false;

		Connection connection = DBConnect.getDatabaseConnection();
		try {
			Statement updateStatement = connection.createStatement();
			
			String updateQuery = "UPDATE Orders SET OrderStatus='" + newStatus + "' WHERE OrderID='" + orderID + "'";
			updateStatement.executeUpdate(updateQuery);		
			
		}catch(SQLException se) {
			se.printStackTrace();
		}finally {
			if(connection != null) {
				try {
					connection.close();
				} catch (SQLException e) {}
			}
		}
		
		//if successful than update isShipped
		isShipped = true;
		
		return isShipped;
	}


}
