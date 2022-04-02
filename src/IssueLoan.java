

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class IssueLoan
 */
public class IssueLoan extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IssueLoan() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String amount = request.getParameter("amount");
		int loanAmount = Integer.parseInt(amount);
		String period = request.getParameter("period");
		String branch = request.getParameter("branch");
		float interest;
		if(loanAmount < 100000){
			interest = 20.0f;
		}else if(loanAmount > 100000 && loanAmount < 500000){
			interest = 22.0f;
		}else if(loanAmount > 500000 && loanAmount < 1000000){
			interest = 25.0f;
		}else if(loanAmount > 1000000 && loanAmount < 1500000){
			interest = 28.0f;
		}else if(loanAmount > 1500000 && loanAmount < 2000000){
			interest = 31.0f;
		}else if(loanAmount > 2000000 && loanAmount < 2500000){
			interest = 34.0f;
		}else{
			interest = 37.0f;
		}
		try{
		Loan l = new Loan();
		l.addLoan(loanAmount, interest, Integer.parseInt(period), Credentials.getName(), branch);
		response.sendRedirect("LoanSuccess.html");
		AccountDepositor ad = new AccountDepositor();
		ad.Deposit(Credentials.getAccountnumber(), loanAmount);
		}catch(Exception e){
			response.sendRedirect("LoanFailed.html");
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
