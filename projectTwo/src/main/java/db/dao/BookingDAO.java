package db.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import model.dto.PriceInfo;
import model.dto.StationInfo;
import model.dto.Ticket;
import model.dto.TranInfo;


public interface BookingDAO {
	public List<Ticket> getAllTranInfo();
	public List<StationInfo> getAllStationInfo(Session session);
	public List<StationInfo> getAllStationInfo();
	public Map<Set<String> , Integer> getAllPriceInfo();
	public List<PriceInfo> getInfoByPrice();
}
