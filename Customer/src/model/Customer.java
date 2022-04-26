package model;
//customer server model
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Customer {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electri?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}
	//insert
	public String insertCustomer(String customerName, String customerAddress, String customerNIC, String customerEmail, String customerPNO) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into custom(`cID`,`customerName`,`customerAddress`,`customerNIC`,`customerEmail`,`customerPNO`)"
					+ " values (?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerName);
			preparedStmt.setString(3, customerAddress);
			preparedStmt.setString(4, customerNIC);
			preparedStmt.setString(5, customerEmail);
			preparedStmt.setString(6, customerPNO);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	//retrieve
	public String readCustomer() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Customer Name</th><th>Customer Address</th><th>Customer NIC</th><th>Customer Email</th><th>Customer Contact No</th></tr>";
			String query = "select * from custom";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String cID = Integer.toString(rs.getInt("cID"));
				String customerName = rs.getString("customerName");
				String customerAddress = rs.getString("customerAddress");
				String customerNIC = rs.getString("customerNIC");
				String customerEmail = rs.getString("customerEmail");
				String customerPNO = rs.getString("customerPNO");

				// Add into the html table
				output += "<tr><td>" + cID + "</td>";
				output += "<td>" + customerName + "</td>";
				output += "<td>" + customerAddress + "</td>";
				output += "<td>" + customerNIC + "</td>";
				output += "<td>" + customerEmail + "</td>";
				output += "<td>" + customerPNO + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the customer.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	//update
	public String updateCustomer(String cID, String customerName, String customerAddress, String customerNIC, String customerEmail, String customerPNO) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE custom SET customerName=?,customerAddress=?,customerNIC=?,customerEmail=?,customerPNO=?" + "WHERE cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, customerName);
			preparedStmt.setString(2, customerAddress);
			preparedStmt.setString(3, customerNIC);
			preparedStmt.setString(4, customerEmail);
			preparedStmt.setString(5, customerPNO);
			preparedStmt.setInt(6, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}
	//delete
	public String deleteCustomer(String cID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from custom where cID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(cID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the customer.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
