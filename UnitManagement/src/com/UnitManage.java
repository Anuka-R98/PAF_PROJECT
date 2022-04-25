package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Unit;

@Path("/Unit")
public class UnitManage {
	Unit UnitObj = new Unit();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUnit() {
		return UnitObj.readUnit();
	}
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUnit(
			@FormParam("customerId") String customerId,			
	 @FormParam("date") String date,
	 @FormParam("units") String units,
	 @FormParam("totalAmount") String totalAmount)
	{
	 String output = UnitObj.insertUnit(customerId, date, units, totalAmount);
	return output;
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateUnit(String unitData)
	{
	//Convert the input string to a JSON object
	 JsonObject cObject = new JsonParser().parse(unitData).getAsJsonObject();
	//Read the values from the JSON object
	 String uID = cObject.get("uID").getAsString();
	 String customerId = cObject.get("customerId").getAsString();
	 String date = cObject.get("date").getAsString();
	 String units = cObject.get("units").getAsString();
	 String totalAmount = cObject.get("totalAmount").getAsString();
	 String output = UnitObj.updateUnit(uID, customerId, date, units, totalAmount);
	return output;
	} 
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUnit(String unitData)
	{
	//Convert the input string to an XML document
	 Document doc = Jsoup.parse(unitData, "", Parser.xmlParser());

	//Read the value from the element <ID>
	 String uID = doc.select("uID").text();
	 String output = UnitObj.deleteUnit(uID);
	return output;
	}
	
}
