package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceInfoDTO {

	private String departure_ST;
	private String destination_ST;
	private int price;

	public PriceInfoDTO() {
	}

	public PriceInfoDTO(ResultSet rs) throws SQLException {
		this.departure_ST = rs.getString("Departure_ST");
		this.destination_ST = rs.getString("Destination_ST");
		this.price = rs.getInt("Price");
	}

	@Override
	public String toString() {
		return "PriceInfoDTO [Departure_ST=" + departure_ST + ", Destination_ST=" + destination_ST + ", Price=" + price
				+ "]";
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
