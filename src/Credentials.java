
public class Credentials {
	static int accountnumber, amount;
	static String name;

	public static String getName() {
		return name;
	}

	public static void setName(String name) {
		Credentials.name = name;
	}

	public static int getAmount() {
		return amount;
	}

	public static void setAmount(int amount) {
		Credentials.amount = amount;
	}

	public static int getAccountnumber() {
		return accountnumber;
	}

	public static void setAccountnumber(int accountnumber) {
		Credentials.accountnumber = accountnumber;
	}
}
