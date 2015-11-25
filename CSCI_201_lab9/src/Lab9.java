import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;


public class Lab9 {
	

	
	public static void main(String[]args)
	{
		
		String firstName;
		String lastName;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection conn = null;
		 try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost/workPage102114", "root", "");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.print("What is the first name?");
		Scanner sc=new Scanner (System.in);
		firstName=sc.nextLine();
		System.out.print("What is the last name?");
		lastName=sc.nextLine();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement("INSERT INTO User (first_name, last_name) VALUES (?,?);");
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			ps.setString(1, firstName);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			ps.setString(2, lastName);
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		try {
			ps.execute();
		} catch (SQLException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		//Step 5
		java.sql.Statement stmt = null;
		try {
			stmt = conn.createStatement();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ResultSet rs = null;
		try {
			rs = stmt.executeQuery("SELECT first_name, last_name FROM User;");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			while (rs.next())
			{
				try {

						System.out.println(rs.getString("first_name"));
						System.out.println(rs.getString("last_name"));
						System.out.println();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
	}
}
