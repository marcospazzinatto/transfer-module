package com.xyz.model;

import java.util.List;

public class Account {
	
	private int nbAccount;
	private double balance;
	
		
	public Account() {
		super();		
	}
	
	public Account(int nbAccount, double balance) {
		super();
		this.nbAccount = nbAccount;
		this.balance = balance;
	}
	public int getNbAccount() {
		return nbAccount;
	}
	public void setNbAccount(int nbAccount) {
		this.nbAccount = nbAccount;
	}
	public Double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}	
	public Account searchAccount(List<Account> accounts, int nbAccount) {
				
		Account foundAccount = null;
		
		if(accounts != null && accounts.size() > 0) {
			for (Account account : accounts) {
				if(account.nbAccount == nbAccount) {
					foundAccount = account;
				}
			}
		}
		
		return foundAccount; 
	}
}
