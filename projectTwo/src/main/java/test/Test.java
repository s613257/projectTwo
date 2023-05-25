package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import db.dao.BaseDAO_MySql;
import model.Utils;

public class Test {

	public static void main(String[] args) throws UnsupportedEncodingException {
		Connection conn = BaseDAO_MySql.getConnection();
		String sql = "INSERT INTO TicketInfo VALUES (?,?,?,?,?,?,?,?,?,?);";
		PreparedStatement pst = null;
		boolean b = true;
		try {

			InputStreamReader isr = new InputStreamReader(
					new FileInputStream(new File("C:\\Users\\User\\Desktop\\MingMin\\project2\\TicketInfo.csv")), "UTF-8");
			BufferedReader br = new BufferedReader(isr);
			String line;
			String[] ars;
			while ((line = br.readLine()) != null) {
				if (b) {
					b = false;
					continue;
				}
				ars = line.split(",");
				pst = conn.prepareStatement(sql);

				pst.setString(1, ars[0]);
				pst.setString(2, ars[1]);
				pst.setString(3, ars[2]);
				pst.setString(4, ars[3]);
				pst.setString(5, ars[4]);
				pst.setString(6, ars[5]);
				pst.setString(7, ars[6]);
				pst.setString(8, ars[7]);
				pst.setString(9, ars[8]);
				pst.setString(10, ars[9]);

				pst.executeUpdate();
				pst.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		Utils.GetDB_PROPERTIES();
		
	}

}



