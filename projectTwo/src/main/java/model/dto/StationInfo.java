package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "StationInfo")
@Component
public class StationInfo {

	@Id @Column(name = "StationID")
	private int stationID;
	
	@Column(name = "StationName")
	private String stationName;

	public StationInfo() {
	}

	public StationInfo(ResultSet rs) throws SQLException {
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
