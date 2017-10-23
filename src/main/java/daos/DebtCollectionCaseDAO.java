package daos;

import java.util.List;

import entities.Customer;
import entities.DebtCollectionCase;

public interface DebtCollectionCaseDAO {
	public DebtCollectionCase get(Long case_id);
	public void save(DebtCollectionCase dcc);
	public void update(DebtCollectionCase dcc);
	public List<DebtCollectionCase> getUnpaidCustomersCases(Customer customer);
}
