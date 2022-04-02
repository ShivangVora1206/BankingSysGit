import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Customer extends DBConnector{
	String CustomerName, CustomerStreet, CustomerCity, CustomerBranch, CustomerMail, CustomerPass;
	int accountno;
	Scanner scanf = new Scanner(System.in);
	void addCustomer(String cn, String cs, String cc, String cb, String mail, String pass){
		CustomerName = cn;
		CustomerCity = cc;
		CustomerStreet = cs;
		CustomerBranch = cb;
		CustomerMail = mail;
		CustomerPass = pass;
		try{
			String query = "insert into customer values(?,?,?,?,?)";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, CustomerName);
			pstmt.setString(2, CustomerStreet);
			pstmt.setString(3, CustomerCity);
			pstmt.setString(4, CustomerMail);
			pstmt.setString(5, CustomerPass);
			int i = pstmt.executeUpdate();
			Account a = new Account();
			if(i>0){
				System.out.println(i+"ROWS ADDED");
				a.addAccount(0, 0, CustomerBranch, CustomerName, CustomerMail);
			}
			else{
				System.out.println("NO ROWS ADDED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE ADDING NEW CUSTOMER");
		}
	}
	void addCustomer(){
		System.out.println("ENTER CUSTOMER NAME: ");
		CustomerName = scanf.next();
		System.out.println("ENTER CUSTOMER STREET: ");
		CustomerStreet = scanf.next();
		System.out.println("ENTER CUSTOMER CITY: ");
		CustomerCity = scanf.next();
		System.out.println("ENTER CUSTOMER BRANCH: ");
		CustomerBranch = scanf.next();
		try{
			String query = "insert into customer values(?,?,?)";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, CustomerName);
			pstmt.setString(2, CustomerStreet);
			pstmt.setString(3, CustomerCity);
			int i = pstmt.executeUpdate();
			Account a = new Account();
			if(i>0){
				System.out.println(i+"ROWS ADDED");
				a.addAccount(0, 0, CustomerBranch, CustomerName, CustomerMail);
				String aquery = "SELECT account_number FROM account where customer_name = ?";
				try {
					PreparedStatement stmt1 = con.prepareStatement(aquery);
					stmt1.setString(1, CustomerName);
					ResultSet rs = stmt1.executeQuery();
					if(rs.next()){
						System.out.println("YOUR ACCOUNT NUMBER IS :"+rs.getInt(1));
						}else{
							System.out.println("");
						}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			else{
				System.out.println("NO ROWS ADDED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE ADDING NEW CUSTOMER");
		}
	}
	void removeCustomer(String cn){
		CustomerName = cn;
		try{
			String query = "delete from account where customer_name=?;";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, CustomerName);
			int i = pstmt.executeUpdate();
			if(i>0){
				System.out.println(i+" ROWS REMOVED");
//				Account a = new Account();
//				String anquery = "select account_number from account where customer_name = "+CustomerName;
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(anquery);
//				if (rs.next()) {
//					int accountno = rs.getInt(1);
//				}
//				a.removeAccount(accountno);
			}
			else{
				System.out.println("NO ROWS REMOVED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE REMOVING CUSTOMER");
		}
	}
	void removeCustomer(){
		System.out.println("ENTER CUSTOMER NAME TO REMOVE: ");
		CustomerName = scanf.next();
		try{
			String query = "delete from account where customer_name=?;";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, CustomerName);
			int i = pstmt.executeUpdate();
			if(i>0){
				System.out.println(i+" ROWS REMOVED");
//				Account a = new Account();
//				String anquery = "select account_number from account where customer_name = "+CustomerName;
//				Statement stmt = con.createStatement();
//				ResultSet rs = stmt.executeQuery(anquery);
//				if (rs.next()) {
//					int accountno = rs.getInt(1);
//				}
//				a.removeAccount(accountno);
			}
			else{
				System.out.println("NO ROWS REMOVED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE REMOVING CUSTOMER");
		}
	}
	
	void DisplayCustomerDetails(){
		String query = "SELECT * FROM customer";
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				System.out.println("CUSTOMER NAME: "+rs.getString(1)+"\nCUTOMER STREET: "+rs.getString(2)+"\nCUSTOMER CITY: "+rs.getInt(3));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	
//	String getCustomerName(){
//		return CustomerName;
//	}
//	String getCustomerStreet(){
//		return CustomerStreet;
//	}
//	String getCustomerCity(){
//		return CustomerCity;
//	}
//	void setCustomerName(String s){
//		CustomerName = s;
//	}
//	void setCustomerStreet(String s){
//		CustomerStreet = s;
//	}
//	void setCustomerCity(String s){
//		CustomerCity = s;
//	}
}
