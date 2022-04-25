package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Unit {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// this sample 1

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/electri?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertUnit(String customerId, String date, String units, String totalAmount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into unit(`uID`,`customerId`,`date`,`units`,`totalAmount`)"
					+ " values (?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerId);
			preparedStmt.setString(3, date);
			preparedStmt.setString(4, units);
			preparedStmt.setString(5, totalAmount);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the unit.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readUnit() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Customer ID</th><th>Date</th><th>Units</th><th>Total Amount</th></tr>";
			String query = "select * from unit";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String uID = Integer.toString(rs.getInt("uID"));
				String customerId = rs.getString("customerId");
				String date = rs.getString("date");
				String units = rs.getString("units");
				String totalAmount = rs.getString("totalAmount");

				// Add into the html table
				output += "<tr><td>" + uID + "</td>";
				output += "<td>" + customerId+ "</td>";
				output += "<td>" + date + "</td>";
				output += "<td>" + units + "</td>";
				output += "<td>" + totalAmount + "</td>";
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the unit management.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updateUnit(String uID, String customerId, String date, String units, String totalAmount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE unit SET customerId=?,date=?,units=?,totalAmount=?" + "WHERE uID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, customerId);
			preparedStmt.setString(2, date);
			preparedStmt.setString(3, units);
			preparedStmt.setString(4, totalAmount);
			preparedStmt.setInt(5, Integer.parseInt(uID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the unit.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deleteUnit(String uID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from unit where uID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(uID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the unit.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}
