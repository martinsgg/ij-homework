package daos;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import entities.Customer;

public class CustomerDAOImpl implements CustomerDAO {
	static Log log = LogFactory.getLog(CustomerDAOImpl.class.getName());
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void save(Customer cust) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(cust);
		tx.commit();
		session.close();
	}

	@Override
	public Customer getCustomer(int customer_id) {
		Customer customer = null;
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			customer = (Customer)session.createQuery("from Customer where customer_id=:customer_id")
					.setParameter("customer_id", new Long(customer_id)).list().get(0);
		} catch(Exception e){
			log.error(e);
		} finally {
			session.close();
		}
		return customer;
	}

	@Override
	public Customer login(String email, String password) {
		Customer customer = null;
		Session session = null;
		try {
			session = this.sessionFactory.openSession();
			customer = (Customer)session.createQuery("from Customer where email=:email and password=:password")
					.setParameter("email", email)
					.setParameter("password", password).list().get(0);
		} catch(Exception e){
			log.error(e);
		} finally {
			session.close();
		}
		return customer;
	}
}
