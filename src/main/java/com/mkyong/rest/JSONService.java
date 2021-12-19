package com.mkyong.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.mkyong.rest.client.MYSQLDML;

@Path("/json/product")
public class JSONService {

	@GET
	@Path("/get")
	@Produces("application/json")
	public Product getProductInJSON() {

		Product product = new Product();
		product.setName("iPad 3");
		product.setQty(999);
		
		return product; 

	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response createProductInJSON(Product product) {

		String result = "Product created : " + product;
		return Response.status(201).entity(result).build();
		
	}
	
	@POST
	@Path("/postSMS")
	@Consumes("application/json")
	public ResponseSMS postSMSData(SMSMonitor smsMonitor) {

		String result = "SmsMonitor created : " + smsMonitor;
		String response = MYSQLDML.dbInsert(smsMonitor.getMsgBody(), smsMonitor.getMsgSentTime(), smsMonitor.getStatus(), smsMonitor.getServiceProvider(), smsMonitor.getMobileNum());
		ResponseSMS resSMS=new ResponseSMS();
		resSMS.setStatus(response);
		if(response.contains("success")) 			
			resSMS.setStatusCode(0);
		else			
			resSMS.setStatusCode(-1);
		
		return resSMS;
		
	}
	
}