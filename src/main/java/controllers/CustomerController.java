package controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import daos.CustomerDAO;
import daos.DebtCollectionCaseDAO;
import entities.Customer;
import entities.DebtCollectionCase;

@EnableAutoConfiguration
@RestController
public class CustomerController {
	
	@Autowired
	protected CustomerDAO customerDAO;
	
	@Autowired
	protected DebtCollectionCaseDAO debtCollectionCaseDAO;
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public Customer register(@RequestBody Customer cust){
		customerDAO.save(cust);
		return cust;
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public List<DebtCollectionCase> loginAndGetCases(@RequestBody Customer cust){
		Customer customer = customerDAO.login(cust.getEmail(), cust.getPassword());
		return debtCollectionCaseDAO.getUnpaidCustomersCases(customer);
	}
	
	@RequestMapping(value = "/Customer", method = RequestMethod.POST)
	public Customer customer(@RequestBody Customer cust){
		customerDAO.save(cust);
		return cust;
	}
}
