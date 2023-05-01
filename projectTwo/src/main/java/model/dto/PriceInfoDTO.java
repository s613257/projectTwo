package model.dto;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PriceInfoDTO {

	private String Departure_ST;
	private String Destination_ST;
	private int Price;

	public PriceInfoDTO() {
	}

	public PriceInfoDTO(ResultSet rs) throws SQLException {
		this.Departure_ST = rs.getString("Departure_ST");
		this.Destination_ST = rs.getString("Destination_ST");
		this.Price = rs.getInt("Price");
	}

	@Override
	public String toString() {
		return "PriceInfoDTO [Departure_ST=" + Departure_ST + ", Destination_ST=" + Destination_ST + ", Price=" + Price
				+ "]";
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

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

}
