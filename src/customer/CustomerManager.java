/**
 * 
 */
package customer;

import java.util.Set;
import customer.Customer;
import dao.AddressDAO;
import dao.CustomerDAO;

/**
 * @author julianareider
 *
 */
public class CustomerManager {
	
	
	private static CustomerDAO dao = new CustomerDAO();
	
	/**
	 * 
	 */
	public CustomerManager() {
		// TODO Auto-generated constructor stub
	}
	
	public void addCustomer(String firstName, String lastname, String usrName, String password) {
		//TODO create new customer
	}
	
	public void loginCustomer(String firstName, String lastname) {
		//TODO Login customer// or find error in information given 
		isValid("", ""); // use this method
	}
	
	private boolean isValid(String userName, String password) {
		//TODO validate user
		return false;
	}
	
	
	public Set<Customer> getAllCustomers(){
		return dao.getAllCustomers();
	}
	
	public Customer getCustomer(String id) {
		return dao.getCustomer(id);
	}
	
	private String createAddressForNewCustomer(String street, String aptNumber, String city, String zipcode, String state) {
		AddressDAO adao = new AddressDAO();	
		return adao.addAddress(aptNumber, street, city, state, zipcode);
	}

	public boolean registerNewCustomer(String firstName, String lastName, String phone, String username, String password, String email, String street, String aptno, String city, String zipcode, String state) {
		Customer c = new Customer();
		c.setFirstName(firstName);
		c.setLastName(lastName);
		c.setPhone(phone);
		c.setUserName(username);
		c.setPassword(password);
		c.setEmail(email);
		return dao.addCustomer(firstName, lastName, phone, username, password, email, 
				createAddressForNewCustomer(street, aptno, city, zipcode, state));
	}

	public void updateCustomer(String id) {
		dao.updateCustomer(id);
	}

	public void deleteCustomer(String id) {
		dao.deleteCustomer(id);
	}
	
	

}
