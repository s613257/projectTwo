package db.dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import java.io.File;
import java.nio.file.Paths;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;

import model.Utils;

public class BaseDAO_MySql {

	private static Connection instance = null;

	public static Connection getConnection() {

		if (instance == null) {
//			try {
//				Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//				instance = DriverManager.getConnection(
//						"jdbc:sqlserver://localhost:1433;databaseName=projectTWO;TrustServerCertificate=True", "sa",
//						"Passw0rd!!");
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			} catch (SQLException se) {
//				se.printStackTrace();
//			}
			try {
//				Context context = new InitialContext();
//				DataSource ds = (DataSource) context.lookup("java:/comp/env/jdbc/projectTWO");
				
				instance = GetDataSource().getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		return instance;
	}

	public static DataSource GetDataSource() {
		BasicDataSource ds = new BasicDataSource();
		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 設定連線的 Driver 名稱
		ds.setUrl("jdbc:sqlserver://localhost:1433;databaseName=projectTWO;TrustServerCertificate=True"); // 資料庫連線位置
		ds.setUsername("sa"); // 帳號
		ds.setPassword("Passw0rd!!"); // 密碼
		ds.setInitialSize(5); // 初始連線數

		ds.setMaxTotal(10); // 最大連線數 以前叫做 maxActive。設定為 10 表示最多能有 10 人同時連線；設定為 0 表示無上限
		ds.setMaxIdle(20); // 最大空閒時間，若是超過時間，連線將被視為不可用。設定為 0 的話表示無上限
		ds.setMaxWaitMillis(10000); // 最大「等待」連線時間，如果超過這個時間，將視為連線逾時。9000 就是 9 秒；設定為 -1 表示無限制

		return ds;
	}

	public static void closeAll(Statement stmt, ResultSet rs) {
		try {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
