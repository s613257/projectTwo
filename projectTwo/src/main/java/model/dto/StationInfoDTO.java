package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StationInfoDTO {

	private int StationID;
	private String StationName;

	public StationInfoDTO() {
	}

	public StationInfoDTO(ResultSet rs) throws SQLException {
		this.StationID = rs.getInt("StationID");
		this.StationName = rs.getString("StationName");
	}

	@Override
	public String toString() {
		return "StationInfoDTO [StationID=" + StationID + ", StationName=" + StationName + "]";
	}

	public int getStationID() {
		return StationID;
	}

	public void setStationID(int stationID) {
		StationID = stationID;
	}

	public String getStationName() {
		return StationName;
	}

	public void setStationName(String stationName) {
		StationName = stationName;
	}

	

}
