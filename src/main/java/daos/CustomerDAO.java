package daos;

import entities.Customer;

public interface CustomerDAO {
	public void save(Customer cust);
	public Customer getCustomer(int customer_id);
	public Customer login(String email,String password);
}
