package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import daos.DebtCollectionCaseDAO;
import entities.DebtCollectionCase;

@EnableAutoConfiguration
@RestController
public class DebtCollectionCaseController {
	
	@Autowired
	protected DebtCollectionCaseDAO debtCollectionCaseDAO;
	
	private final String ACCESS_KEY = "123x55";
	
	@RequestMapping(value = "/DebtCollectionCase", method = RequestMethod.PUT)
	public DebtCollectionCase modifyDebtCollectionCase(@RequestBody DebtCollectionCase dcc,@RequestHeader("Access-Key") String accessKey) throws Exception{
		if(accessKey.equals(ACCESS_KEY)){
			DebtCollectionCase newdcc = debtCollectionCaseDAO.get(dcc.getCase_id());
			if(dcc.getAmount() != null){
				newdcc.setAmount(dcc.getAmount());
			}
			if(dcc.getCustomer_id() != null){
				newdcc.setCustomer_id(dcc.getCustomer_id());
			}
			if(dcc.getDue_date() != null){
				newdcc.setDue_date(dcc.getDue_date());
			}
			newdcc.setPaid(dcc.getPaid());
			debtCollectionCaseDAO.update(newdcc);
			return newdcc;
		} else {
			throw new Exception("Access denied!");
		}
	}
	
	@RequestMapping(value = "/DebtCollectionCase", method = RequestMethod.POST)
	public DebtCollectionCase addDebtCollectionCase(@RequestBody DebtCollectionCase dcc,@RequestHeader("Access-Key") String accessKey) throws Exception{
		if(accessKey.equals(ACCESS_KEY)){
			debtCollectionCaseDAO.save(dcc);
			return dcc;
		} else {
			throw new Exception("Access denied!");
		}
	}
}
