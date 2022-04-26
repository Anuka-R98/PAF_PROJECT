package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Payment;

@Path("/Payment")
public class PaymentManage {
	Payment PaymentObj = new Payment();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readPayment() {
		return PaymentObj.readPayment();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertPayment(
			@FormParam("customerName") String customerName,			
	 @FormParam("billId") String billId,
	 @FormParam("cardNo") String cardNo,
	 @FormParam("cvv") String cvv,
	 @FormParam("expiredDate") String expiredDate,
	 @FormParam("payAmount") String payAmount)
	{
	 String output = PaymentObj.insertPayment(customerName, billId, cardNo, cvv, expiredDate, payAmount);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData)
	{
	//Convert the input string to a JSON object
		 JsonObject cObject = new JsonParser().parse(paymentData).getAsJsonObject();
	//Read the values from the JSON object
	 String pID = cObject.get("pID").getAsString();
	 String customerName = cObject.get("customerName").getAsString();
	 String billId = cObject.get("billId").getAsString();
	 String cardNo = cObject.get("cardNo").getAsString();
	 String cvv = cObject.get("cvv").getAsString();
	 String expiredDate = cObject.get("expiredDate").getAsString();
	 String payAmount = cObject.get("payAmount").getAsString();
	 String output = PaymentObj.updatePayment(pID, customerName, billId, cardNo, cvv, expiredDate, payAmount);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String pID = doc.select("pID").text();
	 String output = PaymentObj.deletePayment(pID);
	return output;
	}
	
}
