

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Transfer
 */
public class Transfer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Transfer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String am = request.getParameter("amount");
		String ra = request.getParameter("recvaccount");
		System.out.println("IN TRANSFER");
		if (Integer.parseInt(am) < 0){
			response.sendRedirect("TransferFailed.html");
		}else{
//			response.sendRedirect("WithdrawFailed.html");

		BankTransfer bt = new BankTransfer();
		bt.Transfer(Credentials.getAccountnumber(),Integer.parseInt(ra), Integer.parseInt(am));
		response.sendRedirect("TransferSuccess.html");
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
