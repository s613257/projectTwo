package db.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import model.dto.StationInfoDTO;
import model.dto.TranInfoDTO;

public interface BookingDAO {
	public List<TranInfoDTO> getAllTranInfo();
	public List<StationInfoDTO> getAllStationInfo();
	public Map<Set<String> , Integer> getAllPriceInfo();
}
