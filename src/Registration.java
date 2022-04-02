

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Registration
 */



public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	boolean ValidateEmail(String e){
		String email = e;
		boolean flag = false;
		DBConnector d = new DBConnector();
		Connection con = d.connect();
		try{
			String query = "select * from customer where email=?";
			PreparedStatement pstmt2 = con.prepareStatement(query);
			pstmt2.setString(1, email);
			ResultSet rs = pstmt2.executeQuery();
			if(rs.next()){
				flag = true;
			}else{
				flag=false;
			}
			
		}catch(Exception ea){
			ea.printStackTrace();
		}
		return flag;
	}
	
	static String hashPwd(String input){
		 try {
	            MessageDigest md = MessageDigest.getInstance("MD5");
	            byte[] messageDigest = md.digest(input.getBytes());
	            BigInteger no = new BigInteger(1, messageDigest);
	            String hashtext = no.toString(16);
	            while (hashtext.length() < 32) {
	                hashtext = "0" + hashtext;
	            }
	            return hashtext;
	        } 
	  
	        catch (NoSuchAlgorithmException e) {
	            throw new RuntimeException(e);
	        }
	}
	
    public Registration() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name = request.getParameter("custName");
		String street = request.getParameter("custStreet");
		String city = request.getParameter("custCity");
		String branch = request.getParameter("custBranch");
		String email = request.getParameter("custMail");
		String pwd = hashPwd(request.getParameter("custPass"));
		Customer c = new Customer();
		try{
			if(ValidateEmail(email)){
				response.sendRedirect("RegistrationIssueE.html");
			}
		c.addCustomer(name, street, city, branch, email, pwd);
		response.sendRedirect("RegistrationSuccess.html");
		}catch(Exception e){
			System.out.println("ERROR WHILE CREATING CUSTOMER");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
