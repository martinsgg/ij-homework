package main;
import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import daos.CustomerDAO;
import daos.DebtCollectionCaseDAO;
import entities.Customer;
import entities.DebtCollectionCase;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(Application.class)
public class CustomerTest {
	static Log log = LogFactory.getLog(CustomerTest.class);
	
	@Autowired
	protected CustomerDAO customerDAO;
	
	@Autowired
	protected DebtCollectionCaseDAO debtCollectionCaseDAO;
	
	@Test public void testCustomerCreation() {
		Customer cust = new Customer();
		cust.setName("Test");
		cust.setSurname("Test");
		cust.setEmail("test@test.lv");
		cust.setPassword("123");
		customerDAO.save(cust);
    }
	
	@Test public void addDebtCollectionCaseForCustomer() {
		Customer cust = customerDAO.getCustomer(1);
		if(cust == null){
			testCustomerCreation();
			cust = customerDAO.getCustomer(1);
		}
		DebtCollectionCase dcc = new DebtCollectionCase();
		dcc.setAmount(new BigDecimal(100.54f));
		log.info("Customer id:"+cust.getCustomer_id());
		dcc.setCustomer_id(cust.getCustomer_id());
		dcc.setDue_date(new Date(System.currentTimeMillis()+(1000*60*24*5)));
		debtCollectionCaseDAO.save(dcc);
    }
}
