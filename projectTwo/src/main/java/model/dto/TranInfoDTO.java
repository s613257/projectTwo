package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TranInfoDTO {
	
	private String tranNo;
	private int stationID;
	private String tran_arrvial_time;

	public TranInfoDTO() {
	}

	public TranInfoDTO(ResultSet rs) throws SQLException {
		this.tranNo = rs.getString("tranNo");
		this.stationID = rs.getInt("StationID");
		this.tran_arrvial_time = rs.getString("tran_arrvial_time");
	}

	@Override
	public String toString() {
		return "TranInfoDTO [TranNo=" + tranNo + ", StationID=" + stationID + ", Train_Arrival_Time=" + tran_arrvial_time + "]";
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

	public String getTran_arrvial_time() {
		return tran_arrvial_time;
	}

	public void setTran_arrvial_time(String tran_arrvial_time) {
		this.tran_arrvial_time = tran_arrvial_time;
	}
}
