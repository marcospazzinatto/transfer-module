package com.xyz.model;

import java.util.Date;

public class LogTransaction {

	private Date dtTransfer;
	private Account accountWithdrawal;
	private Account accountDeposit;
	private double value;	
	
	public LogTransaction() {
		super();		
	}
	
	public LogTransaction(Date dtTransfer, Account accountWithdrawal, Account accountDeposit, double value) {
		super();
		this.dtTransfer = dtTransfer;
		this.accountWithdrawal = accountWithdrawal;
		this.accountDeposit = accountDeposit;
		this.value = value;
	}

	public Date getDtTransfer() {
		return dtTransfer;
	}
	public void setDtTransfer(Date dtTransfer) {
		this.dtTransfer = dtTransfer;
	}
	public Account getAccountWithdrawal() {
		return accountWithdrawal;
	}
	public void setAccountWithdrawal(Account accountWithdrawal) {
		this.accountWithdrawal = accountWithdrawal;
	}
	public Account getAccountDeposit() {
		return accountDeposit;
	}
	public void setAccountDeposit(Account accountDeposit) {
		this.accountDeposit = accountDeposit;
	}
	public double getValue() {
		return value;
	}
	public void setValue(double value) {
		this.value = value;
	}	
}
