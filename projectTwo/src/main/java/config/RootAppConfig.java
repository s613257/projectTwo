package config;

import java.util.Properties;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jndi.JndiObjectFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

//相當於beans.config.xml的java程式組態
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"config","controller","db.dao","model","service"})
@EnableTransactionManagement
public class RootAppConfig {
	
	@Bean
	public DataSource dataSource() throws IllegalArgumentException, NamingException {
		JndiObjectFactoryBean jndiBean = new JndiObjectFactoryBean();
		jndiBean.setJndiName("java:comp/env/connectSqlServerJdbc/SystemService");
//		jndiBean.setJndiName("java:comp/env/connectMySQLJdbc/SystemService");
		jndiBean.afterPropertiesSet();
		DataSource ds = (DataSource)jndiBean.getObject();
		return ds;
	}
	
	@Bean
	public LocalSessionFactoryBean sessionFactory() throws IllegalArgumentException, NamingException {
		LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
		factoryBean.setDataSource(dataSource());
		factoryBean.setPackagesToScan("config","controller","db.dao","model","service");
		factoryBean.setHibernateProperties(additionalProperties());
        return factoryBean;
	}

	private Properties additionalProperties() {
		Properties props = new Properties();
		props.put("hibernate.dialect", org.hibernate.dialect.SQLServerDialect.class);
//		props.put("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class);
		props.put("hibernate.show_sql", Boolean.TRUE);
		props.put("hibernate.format_sql", Boolean.TRUE);
		props.put("hibernate.allow_update_outside_transaction", Boolean.TRUE);
		return props;
	}
	
	@Bean
	public HibernateTransactionManager transactionManager() throws IllegalArgumentException, NamingException {
		HibernateTransactionManager txMgr = new HibernateTransactionManager();
		txMgr.setSessionFactory(sessionFactory().getObject());
		return txMgr;
	}
	
}
