package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StationInfoDTO {

	private int stationID;
	private String stationName;

	public StationInfoDTO() {
	}

	public StationInfoDTO(ResultSet rs) throws SQLException {
		this.stationID = rs.getInt("StationID");
		this.stationName = rs.getString("StationName");
	}

	@Override
	public String toString() {
		return "StationInfoDTO [StationID=" + stationID + ", StationName=" + stationName + "]";
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
}
