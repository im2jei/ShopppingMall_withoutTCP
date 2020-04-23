package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class BasketlistDAO {
	private static Connect c = Connect.getInstance();
	private Connection conn = c.connect();
	private Statement stmt;
	private ResultSet rs;
	BasketlistDTO dto = null;
	private static BasketlistDAO DAOobj;

	private BasketlistDAO() {
	}

	public static BasketlistDAO getInstance() {
		if (DAOobj == null) {
			DAOobj = new BasketlistDAO();
		}
		return DAOobj;
	}

	public boolean Insert(BasketlistDTO dto) {
		boolean result = false;
		c.orclelode();
		try {
			String sql = "insert into basketlist values(b_no.nextval,?,?,?,?,?,?)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setString(1, dto.getId());
			psmt.setInt(2, dto.getCode());
			psmt.setString(3, dto.getCname());
			psmt.setInt(4, dto.getCnt());
			psmt.setInt(5, dto.getPrice());
			psmt.setInt(6, dto.getCheck());
			int r = psmt.executeUpdate();

			if (r > 0) {
				result = true;
			}
			psmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean editinfo(BasketlistDTO dto) {
		boolean result = false;
		c.orclelode();
		try {
			String sql = "update basketlist set check=1 where b_no=? and id=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getCheck());
			psmt.setString(2, dto.getId());
			int r = psmt.executeUpdate();
			System.out.println(dto.getId() + "," + dto.getCode() + "," + dto.getCname() + "," + dto.getCnt() + ","
					+ dto.getPrice() + "," + dto.getCheck());
			if (r > 0) {
				result = true;
			}
			psmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String[]> getList() {
		ArrayList<String[]> list = new ArrayList<String[]>();
		String sql = "SELECT * FROM basketlist";
		try {
			stmt = conn.createStatement();
			if (stmt != null) {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					BasketlistDTO dto = new BasketlistDTO();

					dto.setCode(rs.getInt("code"));
					dto.setCname(rs.getString("cname"));
					dto.setCnt(rs.getInt("cnt"));
					dto.setPrice(rs.getInt("price"));
					list.add(dto.getArray());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
