

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
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	void getAccountnoFromDB(String email){
		DBConnector d = new DBConnector();
		Connection con = d.connect();
		String query = "select account_number from account where email=?";
		try{
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setString(1,  email);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			Credentials.setAccountnumber(rs.getInt(1));
			System.out.println("ACCOUNTNUMER "+rs.getInt(1));
		}
		}catch(Exception e){
			System.out.println("ERROR WHILE GAINING AC NO");
		}
	}
	
	void getNameFromDB(String email){
		DBConnector d = new DBConnector();
		Connection con = d.connect();
		String query = "select customer_name from customer where email=?";
		try{
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1,  email);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				Credentials.setName(rs.getString(1));
//				System.out.println("ACCOUNTNUMER "+rs.getInt(1));
			}
		}catch(Exception e){
			System.out.println("ERROR WHILE GAINING NAME");
		}
	}
	
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
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
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String pass = hashPwd(request.getParameter("password"));
		DBConnector d = new DBConnector();
		Connection con = d.connect();
		try{
			String query = "select * from customer where email=? and pwd=?";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1,  email);
			pstmt.setString(2,  pass);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				getAccountnoFromDB(email);
				getNameFromDB(email);
				response.sendRedirect("HomePage.html");
			}else{
				response.sendRedirect("loginFailed.html");
			}
		}catch(Exception e){
			e.printStackTrace();
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
