package com;
//customer manage RESTful API
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Customer;

@Path("/Customer")
public class CustomerManage {
	Customer CustomerObj = new Customer();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readCustomer() {
		return CustomerObj.readCustomer();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertCustomer(
			@FormParam("customerName") String customerName,			
	 @FormParam("customerAddress") String customerAddress,
	 @FormParam("customerNIC") String customerNIC,
	 @FormParam("customerEmail") String customerEmail,
	 @FormParam("customerPNO") String customerPNO)
	{
	 String output = CustomerObj.insertCustomer(customerName, customerAddress, customerNIC, customerEmail, customerPNO);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateCustomer(String customerData)
	{
	//Convert the input string to a JSON object
	 JsonObject cObject = new JsonParser().parse(customerData).getAsJsonObject();
	//Read the values from the JSON object
	 String cID = cObject.get("cID").getAsString();
	 String customerName = cObject.get("customerName").getAsString();
	 String customerAddress = cObject.get("customerAddress").getAsString();
	 String customerNIC = cObject.get("customerNIC").getAsString();
	 String customerEmail = cObject.get("customerEmail").getAsString();
	 String customerPNO = cObject.get("customerPNO").getAsString();
	 String output = CustomerObj.updateCustomer(cID, customerName, customerAddress, customerNIC, customerEmail, customerPNO);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String customerData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(customerData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String cID = doc.select("cID").text();
	 String output = CustomerObj.deleteCustomer(cID);
	return output;
	}
	
}
