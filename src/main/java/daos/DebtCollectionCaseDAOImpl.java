package daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;

import entities.Customer;
import entities.DebtCollectionCase;

public class DebtCollectionCaseDAOImpl implements DebtCollectionCaseDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public DebtCollectionCase get(Long case_id) {
		Session session = this.sessionFactory.openSession();
		DebtCollectionCase dcc = session.get(DebtCollectionCase.class, case_id);
		session.close();
		return dcc;
	}
	
	@Override
	public void update(DebtCollectionCase dcc) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.update(dcc);
		tx.commit();
		session.close();
	}

	@Override
	public void save(DebtCollectionCase dcc) {
		Session session = this.sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		session.save(dcc);
		tx.commit();
		session.close();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<DebtCollectionCase> getUnpaidCustomersCases(Customer customer) {
		Session session = this.sessionFactory.openSession();
		return session.createQuery("from DebtCollectionCase where customer_id=:customer_id and paid=0 and active=1")
					.setParameter("customer_id", customer.getCustomer_id()).list();
	}
}
