import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class Account extends DBConnector{
	int accountNumber, accountBalance;
	String branchName, customerName;
	Scanner scanf = new Scanner(System.in);
	
	void addAccount(int an, int ab, String bn, String cn, String email){
		accountNumber = an;
		accountBalance = ab;
		branchName = bn;
		customerName = cn;
		try{
			String query = "insert into account values(?,?,?,?,?)";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, accountNumber);
			pstmt.setInt(4, accountBalance);
			pstmt.setString(3, branchName);
			pstmt.setString(2, customerName);
			pstmt.setString(5, email);
			int i = pstmt.executeUpdate();
			if(i>0){
				System.out.println(i+"ROWS ADDED");
			}
			else{
				System.out.println("NO ROWS ADDED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE ADDING NEW ACCOUNT");
		}
	}
	void addAccount(){
		System.out.println("ENTER ACCOUNT NUMBER: ");
		accountNumber = scanf.nextInt();
		System.out.println("ENTER ACCOUNT BALANCE: ");
		accountBalance = scanf.nextInt();
		System.out.println("ENTER CUSTOMER NAME: ");
		customerName = scanf.next();
		System.out.println("ENTER BRANCH NAME: ");
		branchName = scanf.next();
		try{
			String query = "insert into account values(?,?,?,?)";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, accountNumber);
			pstmt.setInt(4, accountBalance);
			pstmt.setString(3, branchName);
			pstmt.setString(2, customerName);
			int i = pstmt.executeUpdate();
			if(i>0){
				System.out.println(i+"ROWS ADDED");
			}
			else{
				System.out.println("NO ROWS ADDED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE ADDING NEW ACCOUNT");
		}
	}
	void removeAccount(int an){
		accountNumber = an;
		try{
			String query = "delete from account where account_number=?;";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, accountNumber);
			int i = pstmt.executeUpdate();
			if(i>0){
				System.out.println(i+" ROWS REMOVED");
			}
			else{
				System.out.println("NO ROWS REMOVED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE REMOVING ACCOUNT");
		}
	}
	void removeAccount(){
		System.out.println("ENTER ACCOUNT NUMBER TO REMOVE: ");
		accountNumber = scanf.nextInt();
		try{
			String query = "delete from account where account_number=?;";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, accountNumber);
			int i = pstmt.executeUpdate();
			if(i>0){
				System.out.println(i+" ROWS REMOVED");
			}
			else{
				System.out.println("NO ROWS REMOVED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE REMOVING ACCOUNT");
		}
	}
	void DisplayAccountDetails(){
		System.out.println("ENTER ACCOUNT NUMBER TO DISPLAY DETAILS:");
		int accountno = scanf.nextInt();
		String query = "SELECT * FROM account where account_number = "+accountno;
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				System.out.println("ACCOUNT NUMBER: "+rs.getInt(1)+"\nCOUSTOMER NAME: "+rs.getString(2)+"\nBRANCH NAME: "+rs.getString(3)+"\nBALANCE: "+rs.getInt(4));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	void setAccountNumber(int a){
//		accountNumber = a;
//	}
//	void setAccountBalance(int a){
//		accountBalance = a;
//	}
//	void setBranchName(String s){
//		branchName = s;
//	}
//	int getAccountNumber(){
//		return accountNumber;
//	}
//	int getAccountBalance(){
//		return accountBalance;
//	}
//	String getBranchName(){
//		return branchName;
//	}
}
