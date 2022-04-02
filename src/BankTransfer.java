import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
public class BankTransfer extends DBConnector{
	int senderAccountNumber, recieverAccountNumber,amount; 
	Scanner scanf = new Scanner(System.in);
	void Transfer(){
		System.out.println("ENTER SENDER'S ACCOUNT NUMBER: ");
		senderAccountNumber = scanf.nextInt();
		System.out.println("ENTER RECIEVER'S ACCOUNT NUMBER: ");
		recieverAccountNumber = scanf.nextInt();
		System.out.println("ENTER TRANSFER AMOUNT: ");
		amount = scanf.nextInt();
		if (CheckExistence(senderAccountNumber) && CheckExistence(recieverAccountNumber)) {
			AccountDepositor ad = new AccountDepositor();
			AccountWithdrawer aw = new AccountWithdrawer();
			try {
				aw.Withdraw(senderAccountNumber, amount);
				ad.Deposit(recieverAccountNumber, amount);
				System.out.println("TRANSFER SUCCESSFUL");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("TRANSFER FAILED");
			}
		} else{
			System.out.println("INVALID ACCOUNT NUMBER");
		}
	}
	
	void Transfer(int san, int ran, int am){
		senderAccountNumber = san;
		System.out.println("SENDER AC NO "+san);
		System.out.println("recv AC NO "+ran);
		recieverAccountNumber = ran;
		amount = am;
		if (CheckExistence(senderAccountNumber) && CheckExistence(recieverAccountNumber)) {
			AccountDepositor ad = new AccountDepositor();
			AccountWithdrawer aw = new AccountWithdrawer();
			try {
				aw.Withdraw(senderAccountNumber, amount);
				ad.Deposit(recieverAccountNumber, amount);
				System.out.println("TRANSFER SUCCESSFUL");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("TRANSFER FAILED");
			}
		} else{
			System.out.println("INVALID ACCOUNT NUMBER");
		}
	}
	boolean CheckExistence(int a){
		String query = "SELECT account_number FROM account";
		boolean flag = false;
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while(rs.next()){
				if(rs.getInt(1)==a){
					flag = true;
					break;
				}
				}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
}
