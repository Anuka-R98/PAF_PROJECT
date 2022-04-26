package model;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Payment {

	private Connection connect() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");// this sample 1

			// Provide the correct details: DBServer/DBName, username, password
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/payments?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
	}

	public String insertPayment(String customerName, String billId, String cardNo, String cvv, String expiredDate, String payAmount) {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for inserting.";
			}
			// create a prepared statement
			String query = " insert into payment(`pID`,`customerName`,`billId`,`cardNo`,`cvv`,`expiredDate`,`payAmount`)"
					+ " values (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, 0);
			preparedStmt.setString(2, customerName);
			preparedStmt.setString(3, billId);
			preparedStmt.setString(4, cardNo);
			preparedStmt.setString(5, cvv);
			preparedStmt.setString(6, expiredDate);
			preparedStmt.setString(7, payAmount);
			// execute the statement
			preparedStmt.execute();
			con.close();
			output = "Inserted successfully";
		} catch (Exception e) {
			output = "Error while inserting the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String readPayment() {
		String output = "";
		try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border=\"1\"><tr><th>ID</th><th>Customer Name</th><th>Bill ID</th><th>Card No</th><th>CVV</th><th>Expired Date</th><th>Paid Amount</th><th>Paid Date and Time</th><th>Update</th><th>Remove</th></tr></tr>";
			String query = "select * from payment";
			Statement stmt = (Statement) con.createStatement();
			ResultSet rs = ((java.sql.Statement) stmt).executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String pID = Integer.toString(rs.getInt("pID"));
				String customerName = rs.getString("customerName");
				String billId = rs.getString("billId");
				String cardNo = rs.getString("cardNo");
				String cvv = rs.getString("cvv");
				String expiredDate = rs.getString("expiredDate");
				String payAmount = rs.getString("payAmount");
				String payDate =rs.getString("payDate");

				// Add into the html table
				output += "<tr><td>" + pID + "</td>";
				output += "<td>" + customerName + "</td>";
				output += "<td>" + billId + "</td>";
				output += "<td>" + cardNo + "</td>";
				output += "<td>" + cvv + "</td>";
				output += "<td>" + expiredDate + "</td>";
				output += "<td>" + payAmount + "</td>";
				output += "<td>" + payDate + "</td>";
				
				// buttons
				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
						 + "<td>"
						 + "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
						 + "<input name='pID' type='hidden' value='" + pID 
						 + "'>" + "</form></td></tr>"; 
				
			}
			con.close();
			// Complete the html table
			output += "</table>";
		} catch (Exception e) {
			output = "Error while reading the payment.";
			System.err.println(e.getMessage());
		}
		return output;
	}

	public String updatePayment(String pID, String customerName, String billId, String cardNo, String cvv, String expiredDate, String payAmount) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for updating.";
			}

			// create a prepared statement
			String query = "UPDATE payment SET customerName=?,billId=?,cardNo=?,cvv=?,expiredDate=?,payAmount=?" + "WHERE pID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setString(1, customerName);
			preparedStmt.setString(2, billId);
			preparedStmt.setString(3, cardNo);
			preparedStmt.setString(4, cvv);
			preparedStmt.setString(5, expiredDate);
			preparedStmt.setString(6, payAmount);
			preparedStmt.setInt(7, Integer.parseInt(pID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Updated successfully";
		} catch (Exception e) {
			output = "Error while updating the payment.";
			System.err.println(e.getMessage());
		}

		return output;
	}

	public String deletePayment(String pID) {
		String output = "";

		try {
			Connection con = connect();

			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}

			// create a prepared statement
			String query = "delete from payment where pID=?";

			PreparedStatement preparedStmt = con.prepareStatement(query);

			// binding values
			preparedStmt.setInt(1, Integer.parseInt(pID));

			// execute the statement
			preparedStmt.execute();
			con.close();

			output = "Deleted successfully";
		} catch (Exception e) {
			output = "Error while deleting the payment.";
			System.err.println(e.getMessage());
		}

		return output;
	}

}

