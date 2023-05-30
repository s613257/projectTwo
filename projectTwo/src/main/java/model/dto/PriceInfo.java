package model.dto;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity @Table(name = "PriceInfo")
public class PriceInfo implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id @Column(name = "DepartureST")
	private String departureST;   
	
	@Id @Column(name ="DestinationST")
	private String destinationST;
	
	@Column(name = "Price")
	private int price;

	public PriceInfo() {
	}

	public PriceInfo(ResultSet rs) throws SQLException {
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
	
	 // 必須重新定義equals()與hashCode()
	public boolean equals(Object obj) {
        if(obj == this) {
            return true;
        }
 
        if(!(obj instanceof PriceInfo)) {
            return false;
        }
 
        PriceInfo user = (PriceInfo) obj;
        return new EqualsBuilder()
                    .append(this.departureST, user.getDepartureST())
                    .append(this.destinationST, user.getDestinationST())
                    .isEquals();
    }
 
    public int hashCode() {
        return new HashCodeBuilder()
                    .append(this.departureST)
                    .append(this.destinationST)
                    .toHashCode();
    }
    
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        PriceInfoDTO that = (PriceInfoDTO) o;
//        return Objects.equals(departureST, that.departureST) &&
//                Objects.equals(destinationST, that.destinationST);
//    }
//
//    @Override
//    public int hashCode() {
//        return Objects.hash(departureST, destinationST);
//    }
}
