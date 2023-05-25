package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import hibernate.bean.BookingTk;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "ticketInfo")
public class TicketDTO {
	
	@Id @Column(name = "TICKETID")	
	private int ticketID;
	
	@Column(name = "TRANNO")
	private String tranNo;
	
	@Column(name = "SEAT")
	private String seat;
	
	@Column(name = "DEPARTUREST")
	private String departureST;
	
	@Column(name = "DESTINATIONST")
	private String destinationST;
	
	@Column(name = "DEPARTUREDATE")
	private String departuredate;
	
	@Column(name = "DEPARTURETIME")
	private String departuretime;
	
	@Column(name = "ARRIVALTIME")
	private String arrivaltime;
	
	@Column(name = "PRICE")
	private int price;
	
	@Column(name = "BOOKINGDATE")
	private String bookingdate;

	public TicketDTO() {
	}
	
	public TicketDTO(BookingTk bkdto) {
		setTicketID(bkdto.getTicketID());
		setTranNo(bkdto.getTranNo());
		setSeat(bkdto.getSeat());
		setDepartureST(bkdto.getDepartureST());
		setDestinationST(bkdto.getDestinationST());
		setdeparturedate(bkdto.getDeparturedate());
		setDeparturetime(bkdto.getDeparturetime());
		setArrivaltime(bkdto.getArrivaltime());
		setPrice(bkdto.getPrice());
		setbookingdate(bkdto.getBookingdate());
	}


	public TicketDTO(ResultSet rs) throws SQLException {
		this.ticketID = rs.getInt("ticketID");
		this.tranNo = rs.getString("tranNo");
		this.seat = rs.getString("seat");
		this.departureST = rs.getString("departureST");
		this.destinationST = rs.getString("destinationST");
		setdeparturedate(rs.getDate("departuredate"));
		this.departuretime = rs.getString("departuretime");
		this.arrivaltime = rs.getString("arrivaltime");
		this.price = rs.getInt("price");
		setbookingdate(rs.getDate("bookingdate"));

	}

	@Override
	public String toString() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return "TicketDTO [ticketID=" + ticketID + ", tranNo=" + tranNo + ", seat=" + seat + ", DepartureST="
				+ departureST + ", DestinationST=" + destinationST + ",departuredate="+ departuredate +", Departuretime=" + departuretime
				+ ", Arrivaltime=" + arrivaltime + ", price=" + price + ", bookingdate=" + bookingdate + "]";
	}

	public String getInsertSql() {
		return String.format("insert into TicketInfo(TicketID,TranNo,Seat,DepartureST,"
				+ "DestinationST,departuredate,Departuretime,Arrivaltime,price,bookingdate) values" + "%s;", getInsertValue());
	}

	public String getInsertValue() {
		return String.format("('%s','%s','%s','%s','%s','%s','%s','%s','%d','%s')", ticketID, tranNo, seat, departureST,
				destinationST, departuredate, departuretime, arrivaltime, price,bookingdate);
	}

	public String getUpdataSql() {
		return String.format(
				"update TicketInfo set " + "TranNo= '%s',Seat='%S',DepartureST='%S',DestinationST='%s',departuredate = '%s',Departuretime='%s',Arrivaltime='%s',price='%s',bookingdate='%s' where TicketID = '%d'",
				tranNo, seat, departureST, destinationST, departuredate, departuretime, arrivaltime, price, bookingdate, ticketID);
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

	public String getDepartureST() {
		return departureST;
	}

	public void setDepartureST(String departureST) {
		this.departureST = departureST;
	}

	public String getDestinationST() {
		return destinationST;
	}

	public void setDestinationST(String destinationST) {
		this.destinationST = destinationST;
	}

	public String getDeparturetime() {
		return departuretime;
	}

	public void setDeparturetime(String departuretime) {
		this.departuretime = departuretime;
	}

	public String getArrivaltime() {
		return arrivaltime;
	}

	public void setArrivaltime(String arrivaltine) {
		arrivaltime = arrivaltine;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getBookingdate() {
		return bookingdate;
	}
	
	public String getDeparturedate() {
		return departuredate;
	}

	public void setbookingdate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bookingdate = sdf.format(date);
	}
	public void setbookingdate(String date) {
		bookingdate = date;
	}
	public void setdeparturedate(Date depdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		departuredate = sdf.format(depdate);
	}
	public void setdeparturedate(String depdate) {
		departuredate = depdate;
	}

}
