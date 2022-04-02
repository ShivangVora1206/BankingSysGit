import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class AccountWithdrawer extends DBConnector{
	int accountNumber, amount, oldBalance, newBalance;
	Scanner scanf = new Scanner(System.in);
	void Withdraw(){
		System.out.println("ENTER ACCOUNT NUMBER: ");
		accountNumber = scanf.nextInt();
		System.out.println("ENTER DEPOSIT AMOUNT: ");
		amount = scanf.nextInt();
		if(amount < 0){
			System.out.println("CANNOT WITHDRAW NEGATIVE AMOUNT");
		}else{
		Connection con = connect();
		String balquery = "select balance from account where account_number = ?";
		try{
			PreparedStatement pstmt = con.prepareStatement(balquery);
			pstmt.setInt(1, accountNumber);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				oldBalance = rs.getInt(1);
			}
			if (oldBalance > amount){
				newBalance = oldBalance-amount;
				String depquery = "update account set balance=? where account_number=?";
				PreparedStatement pstmt2 = con.prepareStatement(depquery);
				pstmt2.setInt(1, newBalance);
				pstmt2.setInt(2, accountNumber);
				int i = pstmt2.executeUpdate();
				if(i > 0){
					System.out.println("WITHDRAW SUCCESSFUL");
				}
				else{
					System.out.println("ACCOUNT NOT FOUND");
				}
			}
			else{
				System.out.println("INSUFFICIENT BALANCE IN ACCOUNT");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		
	}
	void Withdraw(int an, int am){
		accountNumber = an;
		amount = am;
		if (amount < 0){
			System.out.println("CANNOT WITHDRAW NEGATIVE AMOUNT");
		}else{
		Connection con = connect();
		String balquery = "select balance from account where account_number = ?";
		try{
			PreparedStatement pstmt = con.prepareStatement(balquery);
			pstmt.setInt(1, accountNumber);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				oldBalance = rs.getInt(1);
			}
			if (oldBalance > amount){
				newBalance = oldBalance-amount;
				String depquery = "update account set balance=? where account_number=?";
				PreparedStatement pstmt2 = con.prepareStatement(depquery);
				pstmt2.setInt(1, newBalance);
				pstmt2.setInt(2, accountNumber);
				int i = pstmt2.executeUpdate();
				if(i > 0){
					System.out.println("WITHDRAW SUCCESSFUL");
				}
				else{
					System.out.println("ACCOUNT NOT FOUND");
				}
			}
			else{
				System.out.println("INSUFFICIENT BALANCE IN ACCOUNT");
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		}
		
}
}
