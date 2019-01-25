package com.xyz.restful;

import org.junit.Assert;
import org.junit.Test;

import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.header.MediaTypes;
import com.sun.jersey.test.framework.JerseyTest;
import com.sun.jersey.test.framework.WebAppDescriptor;

public class WebServiceAccountTest extends JerseyTest {


    public WebServiceAccountTest() throws Exception {
        super(new WebAppDescriptor.Builder("com.xyz.restful")
                .contextPath("account").build());
    }

    @Test
    public void testApplicationWadl() {
        WebResource webResource = resource();
        String serviceWadl = webResource.path("application.wadl").
                accept(MediaTypes.WADL).get(String.class);

        Assert.assertTrue(serviceWadl.length() > 0);
    }
    
    @Test
    public void testBalanceTransfer() {   	
        
    	WebResource createAccount = resource().path("account/createAccount");
    	Assert.assertEquals("Accounts successfully created", createAccount.get(String.class));
    	WebResource accountTransfer = resource().path("account/transfer/1234/567/50");
    	Assert.assertEquals("Success transaction", accountTransfer.get(String.class));
    	WebResource accountWithdrawal = resource().path("account/balance/1234");
    	Assert.assertEquals("185.41", accountWithdrawal.get(String.class));
    	WebResource accountDeposit = resource().path("account/balance/567");
    	Assert.assertEquals("700.39", accountDeposit.get(String.class));
    	
    }

}

