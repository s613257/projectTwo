package model.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "TicketInfo")
@Component  
public class TicketInfo {
	
	@Id @Column(name = "TicketID")	
	private int ticketID;
	
	@Column(name = "TranNo")
	private String tranNo;
	
	@Column(name = "Seat")
	private String seat;
	
	@Column(name = "DepartureST")
	private String departureST;
	
	@Column(name = "DestinationST")
	private String destinationST;
	
	@Column(name = "DepartureDate")
	private String departuredate;
	
	@Column(name = "DepartureTime")
	private String departuretime;
	
	@Column(name = "ArrivalTime")
	private String arrivaltime;
	
	@Column(name = "Price")
	private int price;
	
	@Column(name = "BookingDate")
	private String bookingdate;

	public TicketInfo() {
	}
	
	public TicketInfo(HighSpeedRailTicket bkdto) {
		setTicketID(bkdto.getTicketID());
		setTranNo(bkdto.getTranNo());
		setSeat(bkdto.getSeat());
		setDepartureST(bkdto.getDepartureST());
		setDestinationST(bkdto.getDestinationST());
		setDeparturedate(bkdto.getDeparturedate());
		setDeparturetime(bkdto.getDeparturetime());
		setArrivaltime(bkdto.getArrivaltime());
		setPrice(bkdto.getPrice());
		setBookingdate(bkdto.getBookingdate());
	}



	@Override
	public String toString() {
		return "TicketDTO [ticketID=" + ticketID + ", tranNo=" + tranNo + ", seat=" + seat + ", DepartureST="
				+ departureST + ", DestinationST=" + destinationST + ",departuredate="+ departuredate +", Departuretime=" + departuretime
				+ ", Arrivaltime=" + arrivaltime + ", price=" + price + ", bookingdate=" + bookingdate + "]";
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

	public void setBookingdate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		bookingdate = sdf.format(date);
	}
	public void setBookingdate(String date) {
		bookingdate = date;
	}
	public void setDeparturedate(Date depdate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		departuredate = sdf.format(depdate);
	}
	public void setDeparturedate(String depdate) {
		departuredate = depdate;
	}

}
