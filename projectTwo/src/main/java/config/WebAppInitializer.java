package config;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletRegistration;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import jakarta.servlet.Filter;
import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRegistration.Dynamic;


//相當於web.xml的java程式組態
public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	                                   
	//設定註冊beans.config.xml程式組態類別
	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootAppConfig.class};
	}

	//設定註冊mvc-servlet.xml程式組態類別
	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebAppConfig.class};
	}

	//設定DispatcherServlet的url-pattern
	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	
	@Override
	protected Filter[] getServletFilters() {
		CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
		characterEncodingFilter.setEncoding("UTF-8");
		characterEncodingFilter.setForceEncoding(true);
		HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
		return new Filter[] {characterEncodingFilter, hiddenHttpMethodFilter};
	}

	//檔案上傳放置檔案位置
	@Override
	protected void customizeRegistration(Dynamic registration) {
		registration.setMultipartConfig(new MultipartConfigElement("c:/temp/upload/"));
	}
	
//	@Override
//	public void onStartup(ServletContext servletContext) throws ServletException {
//		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
//		rootContext.register(RootAppConfig.class,WebAppConfig.class);
//		rootContext.setServletContext(servletContext);
//		Dynamic mvc = servletContext.addServlet("mvc", new DispatcherServlet(rootContext));
//		mvc.setLoadOnStartup(1);
//		mvc.addMapping("/");
//		jakarta.servlet.FilterRegistration.Dynamic filterRegistration = servletContext.addFilter("endcodingFilter", new CharacterEncodingFilter());
//		filterRegistration.setInitParameter("encoding", "UTF-8");
//		filterRegistration.setInitParameter("forceEncoding", "true");
//		filterRegistration = servletContext.addFilter("hiddenHttpMethodFilter",new HiddenHttpMethodFilter());
//		filterRegistration.addMappingForUrlPatterns(null, true, "/*");
//		filterRegistration.addMappingForServletNames(null, true, "mvc");
//
//		servletContext.addListener(new ContextLoaderListener(rootContext));
//	}
}
