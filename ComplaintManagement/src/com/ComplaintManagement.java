package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Complaint;

@Path("/Complaint")
public class ComplaintManagement {
	Complaint ComplaintObj = new Complaint();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readComplaint() {
		return ComplaintObj.readComplaint();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertComplaint(
	 @FormParam("customerName") String customerName,			
	 @FormParam("customerPNO") String customerPNO,
	 @FormParam("description") String description)
	{
	 String output = ComplaintObj.insertComplaint(customerName, customerPNO, description);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateComplaint(String complaintData)
	{
	//Convert the input string to a JSON object
	 JsonObject cObject = new JsonParser().parse(complaintData).getAsJsonObject();
	//Read the values from the JSON object
	 String cID = cObject.get("cID").getAsString();
	 String customerName = cObject.get("customerName").getAsString();
	 String customerPNO = cObject.get("customerPNO").getAsString();
	 String description = cObject.get("description").getAsString();
	
	 String output = ComplaintObj.updateComplaint(cID, customerName, customerPNO, description);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteCustomer(String complaintData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(complaintData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String cID = doc.select("cID").text();
	 String output = ComplaintObj.deleteComplaint(cID);
	return output;
	}
	
}

