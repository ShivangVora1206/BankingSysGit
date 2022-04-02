

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Deposit
 */
public class Deposit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Deposit() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String am = request.getParameter("amount");
		int amount = Integer.parseInt(am);
		if (amount < 0){
			response.sendRedirect("DepositFailed.html");
		}else{
		System.out.println(Integer.parseInt(am));
		Credentials.setAmount(Integer.parseInt(am));
		AccountDepositor d1 = new AccountDepositor();
		int accountnum = Credentials.getAccountnumber();
		d1.Deposit(accountnum, Credentials.getAmount());
		response.sendRedirect("DepositSuccess.html");
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
