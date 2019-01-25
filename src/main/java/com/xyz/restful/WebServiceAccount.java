package com.xyz.restful;
import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.xyz.model.Account;
import com.xyz.model.LogTransaction;

@Path("/account")
public class WebServiceAccount extends Main {
	
	

	@GET    
    @Path("deposit/{nbAccont}/{value}")    
    public void deposit(@PathParam("nbAccont") int nbAccont, @PathParam("value") double value)
    {                
        Account account = new Account().searchAccount(accounts, nbAccont);
        
        if(account != null)
        {
        	account.setBalance(account.getBalance() + value);
        	logTransactions.add(new LogTransaction(new Date(), null, account, value));
        }        
    }
    
    @GET    
    @Path("withdrawal/{nbAccont}/{value}")    
    public void withdrawal(@PathParam("nbAccont") int nbAccont, @PathParam("value") double value)
    {
    	  Account account = new Account().searchAccount(accounts, nbAccont);
          
          if(account != null)
          {
        	  if((account.getBalance() - value) >= 0)
        	  {
        		  account.setBalance(account.getBalance() - value);
        		  logTransactions.add(new LogTransaction(new Date(), account, null , value));
        	  }
        	  else
        	  {
        		  throw new AccountException("Insufficient balance for operation");        		  
        	  }
          }   
    }
    
    @GET    
    @Path("transfer/{nrAccountWithdrawal}/{nbAccountDeposit}/{value}")   
    @Produces(MediaType.TEXT_PLAIN) 
    public String transfer(@PathParam("nrAccountWithdrawal") int nrAccountWithdrawal, @PathParam("nbAccountDeposit") int nbAccountDeposit, @PathParam("value") double value)
    {
    	  Account accountWithdrawal = new Account().searchAccount(accounts, nrAccountWithdrawal);
    	  
    	  Account accountDeposit = new Account().searchAccount(accounts, nbAccountDeposit);
          
          if(accountWithdrawal != null && accountDeposit != null)
          {
        	  withdrawal(nrAccountWithdrawal, value);
        	  deposit(nbAccountDeposit, value);
        	  logTransactions.add(new LogTransaction(new Date(), accountWithdrawal, accountDeposit, value));
          } 
          else
          {
        	  throw new AccountException("Account does not exist");
          }
          
          return "Success transaction";
    }
    
	@GET    
    @Path("balance/{nbAccont}")    
    @Produces(MediaType.TEXT_PLAIN)    
    public String deposit(@PathParam("nbAccont") int nbAccont)
    {                
        Account account = new Account().searchAccount(accounts, nbAccont);
        String balance;
        
        if(account != null)
        {
      	  balance = account.getBalance().toString();
        } 
        else
        {
      	  throw new AccountException("Account does not exist");
        }   
        
        return balance;
    }
	
	@GET    
    @Path("createAccount")   
    public String createAccount()
    {  
		String msgReturn = "Accounts were already created";
		
	        if(accounts == null && logTransactions == null)
	        {
		   	     accounts = new ArrayList<Account>();
		   	     logTransactions = new ArrayList<LogTransaction>();        
		   	        
		   	     Account account1 = new Account(1234,235.41);
		   	     Account account2 = new Account(567,650.39);
		   	     Account account3 = new Account(8901,427.12);
		   	        
		   	     accounts.add(account1);
		   	     accounts.add(account2);
		   	     accounts.add(account3);
		   	     
		   	     msgReturn = "Accounts successfully created";
	        }
        
        return msgReturn;

    }
    
    
}