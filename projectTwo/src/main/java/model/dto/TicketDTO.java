package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketDTO {
	private int TicketID;
	private String TranNo;
	private String Seat;
	private String Departure_ST;
	private String Destination_ST;
	private String Departure_time;
	private String Arrival_time;
	private int price;
	private String Date;

	public TicketDTO() {
	}

	public TicketDTO(ResultSet rs) throws SQLException {
		this.TicketID = rs.getInt("TicketID");
		this.TranNo = rs.getString("TranNo");
		this.Seat = rs.getString("Seat");
		this.Departure_ST = rs.getString("Departure_ST");
		this.Destination_ST = rs.getString("Destination_ST");
		this.Departure_time = rs.getString("Departure_time");
		this.Arrival_time = rs.getString("Arrival_time");
		this.price = rs.getInt("price");
		setDate(rs.getDate("Date"));

	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "TicketDTO [TicketID=" + TicketID + ", TranNo=" + TranNo + ", Seat=" + Seat + ", Departure_ST="
				+ Departure_ST + ", Destination_ST=" + Destination_ST + ", Departure_time=" + Departure_time
				+ ", Arrival_time=" + Arrival_time + ", price=" + price + ", Date=" + sdf.format(Date) + "]";
	}

	public String getInsertSql() {
		return String.format("insert into TicketInfo(TicketID,TranNo,Seat,Departure_ST,"
				+ "Destination_ST,Departure_time,Arrival_time,price,Date) values" + "%s;", getInsertValue());
	}

	public String getInsertValue() {
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return String.format("('%s','%s','%s','%s','%s','%s','%s','%d','%s')", TicketID, TranNo, Seat, Departure_ST,
				Destination_ST, Departure_time, Arrival_time, price, sdf.format(Date));
	}

	public String getUpdataSql() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return String.format(
				"update TicketInfo set " + "TranNo= '%s',Seat='%S',Departure_ST='%S',Destination_ST='%s',Departure_time='%s',Arrival_time='%s',price='%s',Date='%s' where TicketID = '%d'",
				TranNo, Seat, Departure_ST, Destination_ST, Departure_time, Arrival_time, price, sdf.format(Date), TicketID);
	}

	public int getTicketID() {
		return TicketID;
	}

	public void setTicketID(int ticketID) {
		TicketID = ticketID;
	}

	public String getTranNo() {
		return TranNo;
	}

	public void setTranNo(String tranNo) {
		TranNo = tranNo;
	}

	public String getSeat() {
		return Seat;
	}

	public void setSeat(String seat) {
		Seat = seat;
	}

	public String getDeparture_ST() {
		return Departure_ST;
	}

	public void setDeparture_ST(String departure_ST) {
		Departure_ST = departure_ST;
	}

	public String getDestination_ST() {
		return Destination_ST;
	}

	public void setDestination_ST(String destination_ST) {
		Destination_ST = destination_ST;
	}

	public String getDeparture_time() {
		return Departure_time;
	}

	public void setDeparture_time(String departure_time) {
		Departure_time = departure_time;
	}

	public String getArrival_time() {
		return Arrival_time;
	}

	public void setArrival_time(String arrival_tine) {
		Arrival_time = arrival_tine;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date = sdf.format(date);
	}

}
