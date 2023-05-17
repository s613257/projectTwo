package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TranInfoDTO {
	
	private String tranNo;
	private int stationID;
	private String trainArrvialTime;

	public TranInfoDTO() {
	}

	public TranInfoDTO(ResultSet rs) throws SQLException {
		this.tranNo = rs.getString("tranNo");
		this.stationID = rs.getInt("StationID");
		this.trainArrvialTime = rs.getString("tranArrvialTime");
	}

	@Override
	public String toString() {
		return "TranInfoDTO [TranNo=" + tranNo + ", StationID=" + stationID + ", TrainArrivalTime=" + trainArrvialTime + "]";
	}

	public String getTranNo() {
		return tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public int getStationID() {
		return stationID;
	}

	public void setStationID(int stationID) {
		this.stationID = stationID;
	}

	public String getTrainArrvialTime() {
		return trainArrvialTime;
	}

	public void setTrainArrvialTime(String trainArrvialTime) {
		this.trainArrvialTime = trainArrvialTime;
	}

}
