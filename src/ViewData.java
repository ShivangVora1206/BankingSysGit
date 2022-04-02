

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewData
 */
public class ViewData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
	int getBalanceFromDB(){
		int balance=0;
		int accountno = Credentials.getAccountnumber();
		DBConnector d = new DBConnector();
		Connection con = d.connect();
		String query = "select balance from account where account_number=?";
		try{
		PreparedStatement pstmt = con.prepareStatement(query);
		pstmt.setInt(1,  accountno);
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()){
			
			System.out.println("BALANCE "+rs.getInt(1));
		balance = rs.getInt(1);
		}
		}catch(Exception e){
			System.out.println("ERROR WHILE GAINING AC NO");
		}
		return balance;
	}
	
    public ViewData() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bal = getBalanceFromDB();
		response.setContentType("text/html");  
		PrintWriter out = response.getWriter();  
		out.println("<html>"+
					"<head>"+
					"<meta charset=\"ISO-8859-1\">"+
					"<title>Insert title here</title>"+
					"</head>"+
					"<body>"+
					"current balane = :"+bal+
					"<form action=\"HomePage.html\">"+
					"<button type=\"submit\">hehe</button>"+
					"</form>"+
					"</body>"+
					"</html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
