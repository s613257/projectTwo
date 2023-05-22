package hibernate.bean;

import java.text.SimpleDateFormat;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import model.dto.TicketDTO;

@Entity @Table(name = "booking_Tk")
public class BookingTk {
	
	@Id @Column(name = "ticketID")
	private int ticketID;
	
	@Column(name = "tranNo")
	private String tranNo;
	
	@Column(name = "seat")
	private String seat;
	
	@Column(name = "departureST")
	private String departureST;
	
	@Column(name = "destinationST")
	private String destinationST;
	
	@Column(name = "departuredate")
	private String departuredate;
	
	@Column(name = "departuretime")
	private String departuretime;
	
	@Column(name = "arrivaltime")
	private String arrivaltime;
	
	@Column(name = "price")
	private int price;
	
	@Column(name = "bookingdate")
	private String bookingdate;

	public BookingTk() {
		
	}
	
	public BookingTk(TicketDTO tickDto) {
		setTicketID(tickDto.getTicketID());
		setTranNo(tickDto.getTranNo());
		setSeat(tickDto.getSeat());
		setDepartureST(tickDto.getDepartureST());
		setDestinationST(tickDto.getDestinationST());
		setdeparturedate(tickDto.getDeparturedate());
		setDeparturetime(tickDto.getDeparturetime());
		setArrivaltime(tickDto.getArrivaltime());
		setPrice(tickDto.getPrice());
		setbookingdate(tickDto.getBookingdate());
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
	
	@Override
	public String toString() {
		return "BookingTk [ticketID=" + ticketID + ", tranNo=" + tranNo + ", seat=" + seat + ", DepartureST="
				+ departureST + ", DestinationST=" + destinationST + ",departuredate="+ departuredate +", Departuretime=" + departuretime
				+ ", Arrivaltime=" + arrivaltime + ", price=" + price + ", bookingdate=" + bookingdate + "]";
	}
}
