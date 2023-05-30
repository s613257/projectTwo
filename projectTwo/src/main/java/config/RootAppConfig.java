package config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


//相當於beans.config.xml的java程式組態
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"config","controller","db","model","service"})
@EnableTransactionManagement
//@Configuration
//@EnableTransactionManagement
public class RootAppConfig {
	Environment env;

	@Autowired
	public void setEnv(Environment env) {
		this.env = env;
	}

	@Bean
	public static DataSource dataSource() throws IllegalArgumentException, NamingException {
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

	@Bean(name = "wired_sessionFactory", destroyMethod = "destroy")
	public static LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
		factory.setDataSource(dataSource());
		factory.setPackagesToScan(new String[] { "config","controller","db","model","service"});
		factory.setHibernateProperties(additionalProperties());
		
		return factory;
	}

	private static Properties additionalProperties() {
		Properties properties = new Properties();
		properties.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
		properties.put("hibernate.show_sql", Boolean.FALSE);
		properties.put("hibernate.format_sql", Boolean.FALSE);
		properties.put("hibernate.current_session_context_class", "thread");
		properties.put("default_batch_fetch_size", 10);
		properties.put("hibernate.hbm2ddl.auto", "update");
		return properties;
	}

	@Bean(name = "transactionManager")
	@Autowired
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		HibernateTransactionManager txManager = new HibernateTransactionManager();
		txManager.setSessionFactory(sessionFactory);
		return txManager;
	}
//	Environment env;
//
//	@Autowired
//	public void setEnv(Environment env) {
//		this.env = env;
//	}
//	
//	@Bean
//	public DataSource dataSource() throws IllegalArgumentException, NamingException {
//		BasicDataSource ds = new BasicDataSource();
//		ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); // 設定連線的 Driver 名稱
//		ds.setUrl("jdbc:sqlserver://localhost:1433;databaseName=projectTWO;TrustServerCertificate=True"); // 資料庫連線位置
//		ds.setUsername("sa"); // 帳號
//		ds.setPassword("Passw0rd!!"); // 密碼
//		ds.setInitialSize(5); // 初始連線數
//		ds.setMaxTotal(10); // 最大連線數 以前叫做 maxActive。設定為 10 表示最多能有 10 人同時連線；設定為 0 表示無上限
//		ds.setMaxIdle(20); // 最大空閒時間，若是超過時間，連線將被視為不可用。設定為 0 的話表示無上限
//		ds.setMaxWaitMillis(10000); // 最大「等待」連線時間，如果超過這個時間，將視為連線逾時。9000 就是 9 秒；設定為 -1 表示無限制
//		return ds;
//	}
//
//	
//	@Bean(name = "sessionFactory", destroyMethod = "destroy")
//    public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
//        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
//        sessionFactory.setDataSource(dataSource());
//        sessionFactory.setPackagesToScan("config","controller","db.dao","model","service"); 
//        sessionFactory.setHibernateProperties(additionalProperties());  
//        return sessionFactory;
//    }
//
//	private Properties additionalProperties() {
//		Properties props = new Properties();
//		props.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
//		props.put("hibernate.show_sql", Boolean.TRUE);
//		props.put("hibernate.format_sql", Boolean.TRUE);
//		props.put("hibernate.allow_update_outside_transaction", Boolean.TRUE);
//		return props;
//	}
//	
//
//	
////	@Bean
////    public HibernateTransactionManager transactionManager(LocalSessionFactoryBean sessionFactory) {
////        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
////        transactionManager.setSessionFactory(sessionFactory.getObject());
////        return transactionManager;
////    }
//	@Bean(name = "transactionManager")
//	@Autowired
//	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
//		HibernateTransactionManager txManager = new HibernateTransactionManager();
//		txManager.setSessionFactory(sessionFactory);
//		return txManager;
//	}
	
}
