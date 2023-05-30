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

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import db.dao.BaseDAO_Hibernate;
import db.dao.BaseDAO_MySql;
import db.dao.BookingDAO;
import jakarta.transaction.Transactional;
import model.dto.PriceInfo;
import model.dto.StationInfo;
import model.dto.Ticket;
import model.dto.TranInfo;

@Repository
@Transactional
public class BookingDAOImpl extends BaseDAO_Hibernate implements BookingDAO {
	

	@Override
	public List<Ticket> getAllTranInfo() {
		return getAllTranInfoBySql("select "
				+ "TicketID = 0, "
				+ "BookingDate = getdate(), "
				+ "price = 0, "
				+ "Seat = 0, "
				+ "DepartureDate =  getdate(), "
				+ "dep_st.TranNo TranNo, "
				+ "dep_st.StationID DepartureST,"
				+ "dep_st.TrainArrivalTime Departuretime, "
				+ "des_st.StationID DestinationST,"
				+ "des_st.TrainArrivalTime Arrivaltime "
				+ "from TranInfo dep_st " 
				+ "left join TranInfo des_st on dep_st.TranNo = des_st.TranNo and dep_st.TrainArrivalTime <> des_st.TrainArrivalTime "
				+ "and des_st.TrainArrivalTime > dep_st.TrainArrivalTime "
				+ "where des_st.StationID is not null;");
	}
	@Override
	public List<StationInfo> getAllStationInfo() {
		return getAllStationInfo(getSession());
	}
	@Override
	public List<StationInfo> getAllStationInfo(Session session) {
		Query<StationInfo> query = session.createQuery("from StationInfo", StationInfo.class);
		return query.list();
	} 
	

	@Override
	public Map<Set<String> , Integer> getAllPriceInfo() {
		List<PriceInfo> priceList = getInfoByPrice();
		Map<Set<String> , Integer> result = new HashMap<Set<String> , Integer>();
		for(PriceInfo price: priceList) {
			Set<String> tmpS = new HashSet<String>();
			tmpS.add(price.getDepartureST());
			tmpS.add(price.getDestinationST());
			result.put(tmpS, price.getPrice());
		}		
		return result;
	}

	private List<Ticket> getAllTranInfoBySql(String sql) {
		List<Ticket> resultTranList = new ArrayList<Ticket>();
		Query<Ticket> query = getSession().createQuery(sql,Ticket.class);
		resultTranList= query.list();
		return resultTranList;
	}

	private List<StationInfo> getInfoByStation() {
		Query<StationInfo> query = getSession().createQuery("from StationInfo",StationInfo.class);
		return query.list();
	}
	
	@Override
	public List<PriceInfo> getInfoByPrice() {
		Query<PriceInfo> query = getSession().createQuery("from PriceInfo",PriceInfo.class);
		return query.list();
	}

}
