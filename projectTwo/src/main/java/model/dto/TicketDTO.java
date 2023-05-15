package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketDTO {
	private int ticketID;
	private String tranNo;
	private String seat;
	private String departure_ST;
	private String destination_ST;
	private String departure_date;
	private String departure_time;
	private String arrival_time;
	private int price;
	private String booking_date;

	public TicketDTO() {
	}

	public TicketDTO(ResultSet rs) throws SQLException {
		this.ticketID = rs.getInt("ticketID");
		this.tranNo = rs.getString("tranNo");
		this.seat = rs.getString("seat");
		this.departure_ST = rs.getString("departure_ST");
		this.destination_ST = rs.getString("destination_ST");
		setdeparture_date(rs.getDate("departure_date"));
		this.departure_time = rs.getString("departure_time");
		this.arrival_time = rs.getString("Arrival_time");
		this.price = rs.getInt("price");
		setbooking_date(rs.getDate("booking_date"));

	}

	@Override
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "TicketDTO [ticketID=" + ticketID + ", tranNo=" + tranNo + ", seat=" + seat + ", Departure_ST="
				+ departure_ST + ", Destination_ST=" + destination_ST + ",departure_date="+ sdf.format(departure_date) +", Departure_time=" + departure_time
				+ ", Arrival_time=" + arrival_time + ", price=" + price + ", booking_date=" + sdf.format(booking_date) + "]";
	}

	public String getInsertSql() {
		return String.format("insert into TicketInfo(TicketID,TranNo,Seat,Departure_ST,"
				+ "Destination_ST,departure_date,Departure_time,Arrival_time,price,booking_date) values" + "%s;", getInsertValue());
	}

	public String getInsertValue() {
		return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%d','%s')", ticketID, tranNo, seat, departure_ST,
				destination_ST, departure_date, departure_time, arrival_time, price,booking_date);
	}

	public String getUpdataSql() {
		return String.format(
				"update TicketInfo set " + "TranNo= '%s',Seat='%S',Departure_ST='%S',Destination_ST='%s',departure_date = '%s',Departure_time='%s',Arrival_time='%s',price='%s',booking_date='%s' where TicketID = '%d'",
				tranNo, seat, departure_ST, destination_ST, departure_date, departure_time, arrival_time, price, booking_date, ticketID);
	}

	

	public int getTicketID() {
		return ticketID;
	}

	public void setTicketID(int ticketID) {
		this.ticketID = ticketID;
	}

	public String getTranNo() {
		return tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public String getSeat() {
		return seat;
	}

	public void setSeat(String seat) {
		this.seat = seat;
	}

	public String getDeparture_ST() {
		return departure_ST;
	}

	public void setDeparture_ST(String departure_ST) {
		this.departure_ST = departure_ST;
	}

	public String getDestination_ST() {
		return destination_ST;
	}

	public void setDestination_ST(String destination_ST) {
		this.destination_ST = destination_ST;
	}

	public String getDeparture_time() {
		return departure_time;
	}

	public void setDeparture_time(String departure_time) {
		this.departure_time = departure_time;
	}

	public String getArrival_time() {
		return arrival_time;
	}

	public void setArrival_time(String arrival_tine) {
		arrival_time = arrival_tine;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBooking_date() {
		return booking_date;
	}
	
	public String getDeparture_date() {
		return booking_date;
	}

	public void setbooking_date(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		booking_date = sdf.format(date);
	}
	public void setbooking_date(String date) {
		booking_date = date;
	}
	public void setdeparture_date(Date dep_date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		departure_date = sdf.format(dep_date);
	}
	public void setdeparture_date(String dep_date) {
		departure_date = dep_date;
	}

}
