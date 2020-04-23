package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connect {
	public static Connect c = null;
	private Connection conn;

	private Connect() {

	}

	public static Connect getInstance() {
		if (c == null) {
			c = new Connect();
		}
		return c;
	}

	public void orclelode() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("클래스 로드 성공");
		} catch (ClassNotFoundException e) {
			System.out.println("클래스 로드 실패");
		}

	}

	public Connection connect() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:orcl", "system", "11111111");
			System.out.println("db접속 성공");
		} catch (SQLException e) {
			System.out.println("실패");
			e.printStackTrace();
		}
		return conn;
	}
}
