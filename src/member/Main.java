package member;

import java.net.Socket;

public class Main {

	public static void main(String[] args) throws Exception {
		Socket withServer = null;
		withServer = new Socket("10.0.0.67", 9999);
		new Login();
	}

}
