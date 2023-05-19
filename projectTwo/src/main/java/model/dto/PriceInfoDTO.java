package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceInfoDTO {

	private String departureST;
	private String destinationST;
	private int price;

	public PriceInfoDTO() {
	}

	public PriceInfoDTO(ResultSet rs) throws SQLException {
		this.departureST = rs.getString("departureST");
		this.destinationST = rs.getString("destinationST");
		this.price = rs.getInt("Price");
	}

	@Override
	public String toString() {
		return "PriceInfoDTO [DepartureST=" + departureST + ", DestinationST=" + destinationST + ", Price=" + price
				+ "]";
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
