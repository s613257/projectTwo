package db.dao.impl;

import java.nio.channels.SelectableChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import db.dao.BaseDAO_MySql;
import db.dao.BookingDAO;
import model.dto.PriceInfoDTO;
import model.dto.StationInfoDTO;
import model.dto.TranInfoDTO;

public class BookingDAOImpl extends BaseDAO_MySql implements BookingDAO {
	

	@Override
	public List<TranInfoDTO> getAllTranInfo() {
		return getInfoByTran("select * from TranInfo");
	}

	@Override
	public List<StationInfoDTO> getAllStationInfo() {
		return getInfoByStation("select * from StationInfo");
	}

	@Override
	public Map<Set<String> , Integer> getAllPriceInfo() {
		List<PriceInfoDTO> priceList = getInfoByPrice("select * from PriceInfo");
		Map<Set<String> , Integer> result = new HashMap<Set<String> , Integer>();
		for(PriceInfoDTO price: priceList) {
			Set<String> tmpS = new HashSet<String>();
			tmpS.add(price.getDeparture_ST());
			tmpS.add(price.getDestination_ST());
			result.put(tmpS, price.getPrice());
		}		
		return result;
	}

	private List<TranInfoDTO> getInfoByTran(String sql) {
		
		List<TranInfoDTO> resultTranList = new ArrayList<TranInfoDTO>();
		Statement st = null;
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				resultTranList.add(new TranInfoDTO(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(st, rs);
		}
		return resultTranList;
	}

	private List<StationInfoDTO> getInfoByStation(String sql) {
		List<StationInfoDTO> resultStatuinList = new ArrayList<StationInfoDTO>();
		Statement st = null;
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				resultStatuinList.add(new StationInfoDTO(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(st, rs);
		}
		return resultStatuinList;

	}

	private List<PriceInfoDTO> getInfoByPrice(String sql) {
		List<PriceInfoDTO> resultPriceList = new ArrayList<PriceInfoDTO>();
		Statement st = null;
		ResultSet rs = null;
		try {
			Connection conn = getConnection();
			st = conn.createStatement();
			rs = st.executeQuery(sql);
			while (rs.next()) {
				resultPriceList.add(new PriceInfoDTO(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeAll(st, rs);
		}
		return resultPriceList;
	}
}
