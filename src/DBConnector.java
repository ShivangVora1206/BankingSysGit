import java.sql.DriverManager;
import java.sql.Connection;
public class DBConnector {
	public Connection connect(){
		Connection con = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/bankingsystemV1","root","");
		}catch(Exception e){
			e.printStackTrace();
		}
	return con;
	}
}
