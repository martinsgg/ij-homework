package entities;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name="debt_collection_case")
public class DebtCollectionCase {
	@Id
	@GeneratedValue(generator="increment")
	@GenericGenerator(name="increment", strategy = "increment") 
	@Column(name="case_id")
	private Long case_id;
	
	@Column(name="customer_id")
	private Long customer_id;
	
	@Column(name="due_date")
	private Date due_date;
	
	@Column(name="amount")
	private BigDecimal amount;
	
	@Column(name="paid")
	private boolean paid = false;

	public Long getCase_id() {
		return case_id;
	}

	public void setCase_id(Long case_id) {
		this.case_id = case_id;
	}

	public Long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(Long customer_id) {
		this.customer_id = customer_id;
	}

	public Date getDue_date() {
		return due_date;
	}

	public void setDue_date(Date due_date) {
		this.due_date = due_date;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public boolean getPaid() {
		return paid;
	}

	/**
	 * If payment day is due ant paid is false, customer has not paid. If true payment has been made.
     */
	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
