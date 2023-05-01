package db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import db.dao.BaseDAO_MySql;
import db.dao.TicketDAO;
import model.dto.TicketDTO;

public class TicketDAOImpl extends BaseDAO_MySql implements TicketDAO {
	Connection conn = getConnection();

	@Override
	public List<TicketDTO> getAllTicketInfo() {
		return getInfoBySql("select * from TicketInfo");

	}

	@Override
	public int insertTicketInfo(TicketDTO data) {
		String sql = data.getInsertSql();
		System.out.println(sql);
		return executeUpdate(sql);

	}
	@Override
	public int updateTicketInfo(TicketDTO dataSource) {
		String sql = dataSource.getUpdataSql();
		System.out.println(sql);
		return executeUpdate(sql);
	}
	
	@Override
	public int deleteTicketInfo(int TicketID) {
		return executeUpdate(String.format("delete from TicketInfo where TicketID = %d;",TicketID));
	}

	private List<TicketDTO> getInfoBySql(String sql) {
		List<TicketDTO> resultList = new ArrayList<TicketDTO>();
		Statement st = null;
		ResultSet rs = null;
		try {
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				resultList.add(new TicketDTO(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(st, rs);
		}
		return resultList;

	}

	private int executeUpdate(String sql) {
		Statement st = null;
		int effectRow = 0;
		try {
			st = conn.createStatement();
			effectRow = st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(st, null);
		}
		return effectRow;

	}
}
