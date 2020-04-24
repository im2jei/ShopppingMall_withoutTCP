package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class GoodsDAO {
	private static Connect c = Connect.getInstance();
	private Connection conn = c.connect();
	private Statement stmt;
	private ResultSet rs;
	GoodsDTO dto = null;
	private static GoodsDAO DAOobj;

	private GoodsDAO() {
	}

	public static GoodsDAO getInstance() {
		if (DAOobj == null) {
			DAOobj = new GoodsDAO();
		}
		return DAOobj;
	}

	public boolean Insert(GoodsDTO dto2) {
		boolean result = false;
		c.orclelode();
		try {
			String sql = "insert into goods values(?,?,?,?,0)";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto2.getCode());
			psmt.setString(2, dto2.getCname());
			psmt.setInt(3, dto2.getCnt());
			psmt.setInt(4, dto2.getPrice());
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

	public boolean editinfo(GoodsDTO dto) {
		boolean result = false;
		c.orclelode();
		try {
			String sql = "update goods set code=?,cname=?,cnt=?,price=? chk=? where code=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getCode());
			psmt.setString(2, dto.getCname());
			psmt.setInt(3, dto.getCnt());
			psmt.setInt(4, dto.getPrice());
			psmt.setInt(5, dto.getCode());
			psmt.setInt(6, dto.getCheck());
			int r = psmt.executeUpdate();
			System.out.println(dto.getCode() + "," + dto.getCname() + "," + dto.getCnt() + "," + dto.getPrice() + ","
					+ dto.getCheck());
			if (r > 0) {
				result = true;
			}
			psmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	public boolean delinfo(GoodsDTO dto) {
		boolean result = false;
		c.orclelode();
		try {
			String sql = "delete goods where code=?";
			PreparedStatement psmt = conn.prepareStatement(sql);
			psmt.setInt(1, dto.getCode());
			int r = psmt.executeUpdate();
			System.out.println(dto.getCode() + "," + dto.getCname() + "," + dto.getCnt() + "," + dto.getPrice());
			if (r > 0) {
				result = true;
			}
			psmt.close();

		} catch (SQLException e) {
			System.out.println("삭제실패");
		}
		return result;

	}

	public ArrayList<String[]> getList() {
		ArrayList<String[]> list = new ArrayList<String[]>();
		String sql = "SELECT * FROM goods";
		try {
			stmt = conn.createStatement();
			if (stmt != null) {
				rs = stmt.executeQuery(sql);
				while (rs.next()) {
					GoodsDTO dto = new GoodsDTO();

					dto.setCode(rs.getInt("code"));
					dto.setCname(rs.getString("cname"));
					dto.setCnt(rs.getInt("cnt"));
					dto.setPrice(rs.getInt("price"));
					dto.setCheck(rs.getInt("ox"));
					list.add(dto.getArray());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return list;
	}

}
