import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Loan extends DBConnector{
	int loanNumber,loanAmount,period;
	float interestRate;
	String branchName, customerName;
Scanner scanf = new Scanner(System.in);
	
	void addLoan(int la, float in, int p, String name, String branch){
//		Customer c = new Customer();
//		c.addCustomer();
//		loanNumber = ln;
		loanAmount = la;
		branchName = branch;
		customerName = name;
		interestRate = in;
		period = p;
		try{
			String query = "insert into loan values(?,?,?,?,?,?)";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, loanNumber);
			pstmt.setInt(4, loanAmount);
			pstmt.setString(3, branchName);
			pstmt.setString(2, customerName);
			pstmt.setFloat(5, interestRate);
			pstmt.setFloat(6, period);
			
			int i = pstmt.executeUpdate();
			if(i>0){
				System.out.println(i+"ROWS ADDED");

			}
			else{
				System.out.println("NO ROWS ADDED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE ADDING NEW LOAN");
		}
	}
	void addLoan(){
		Customer c = new Customer();
		c.addCustomer();
		System.out.println("ENTER LOAN AMOUNT: ");
		loanAmount = scanf.nextInt();
//		System.out.println("CUSTOMER NAME: "+c.CustomerName);
		customerName = c.CustomerName;
		branchName = c.CustomerBranch;
		System.out.println("ENTER LOAN PERIOD: ");
		period = scanf.nextInt();
		System.out.println("ENTER INTEREST RATE: ");
		interestRate = scanf.nextFloat();
		
		try{
			String query = "insert into loan values(?,?,?,?,?,?)";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, loanNumber);
			pstmt.setInt(4, loanAmount);
			pstmt.setString(3, branchName);
			pstmt.setString(2, customerName);
			pstmt.setFloat(5, interestRate);
			pstmt.setInt(6, period);
			int i = pstmt.executeUpdate();
			if(i>0){
				System.out.println(i+"ROWS ADDED");

			}
			else{
				System.out.println("NO ROWS ADDED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE ADDING NEW LOAN");
		}
	}
	void removeLoan(int an){
		loanNumber = an;
		try{
			String query = "delete from loan where loan_number=?;";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, loanNumber);
			int i = pstmt.executeUpdate();
			if(i>0){
				System.out.println(i+" ROWS REMOVED");
			}
			else{
				System.out.println("NO ROWS REMOVED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE REMOVING LOAN");
		}
	}
	void removeLoan(){
		System.out.println("ENTER ACCOUNT NUMBER TO REMOVE: ");
		loanNumber = scanf.nextInt();
		try{
			String query = "delete from loan where loan_number=?;";
			Connection con = connect();
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setInt(1, loanNumber);
			int i = pstmt.executeUpdate();
			if(i>0){
				System.out.println(i+" ROWS REMOVED");
			}
			else{
				System.out.println("NO ROWS REMOVED");
			}
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("ERROR WHILE REMOVING LOAN");
		}
	}
	void DisplayLoanDetails(){
		System.out.println("ENTER LOAN NUMBER TO DISPLAY DETAILS:");
		int loanno = scanf.nextInt();
		String query = "SELECT * FROM loan where loan_number = "+loanno;
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				System.out.println("LOAN NUMBER: "+rs.getInt(1)+"\nCOUSTOMER NAME: "+rs.getString(2)+"\nBRANCH NAME: "+rs.getString(3)+"\nLOAN AMOUNT: "+rs.getInt(4)+"\nINTEREST RATE: "+rs.getFloat(5)+"\nLOAN PERIOD: "+rs.getInt(6));
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	void setLoanNumber(int ln){
//		loanNumber = ln;
//	}
//	void setLoanAmount(int ln){
//		loanAmount = ln;
//	}
//	void setBranchName(String s){
//		branchName = s;
//	}
//	int getLoanNumber(){
//		return loanNumber;
//	}
//	int getLoanAmount(){
//		return loanAmount;
//	}
//	String getBranchName(){
//		return branchName;
	}

