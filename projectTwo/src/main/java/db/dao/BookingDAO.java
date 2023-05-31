package db.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;


import model.dto.PriceInfo;
import model.dto.StationInfo;
import model.dto.TicketInfo;


public interface BookingDAO {
	public List<TicketInfo> getAllTranInfo();
	public List<StationInfo> getAllStationInfo();
	public Map<Set<String> , Integer> getAllPriceInfo();
	public List<PriceInfo> getInfoByPrice();
}
