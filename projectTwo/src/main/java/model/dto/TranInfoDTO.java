package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TranInfoDTO {
	
	private String TranNo;
	private int StationID;
	private String Time;

	public TranInfoDTO() {
	}

	public TranInfoDTO(ResultSet rs) throws SQLException {
		this.TranNo = rs.getString("TranNo");
		this.StationID = rs.getInt("StationID");
		this.Time = rs.getString("Time");
	}

	@Override
	public String toString() {
		return "TranInfoDTO [TranNo=" + TranNo + ", StationID=" + StationID + ", Time=" + Time + "]";
	}
	
	public String getTranNo() {
		return TranNo;
	}

	public void setTranNo(String tranNo) {
		TranNo = tranNo;
	}

	public int getStationID() {
		return StationID;
	}

	public void setStationID(int stationID) {
		StationID = stationID;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

}
