package com.aun.playground.spring.boot;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.sun.faces.config.ConfigureListener;

@Configuration
public class ApplicationConfig extends SpringBootServletInitializer implements ServletContextAware {

	@Override
	public void onStartup(ServletContext context) throws ServletException {
		super.onStartup(context);
		context.setInitParameter("primefaces.CLIENT_SIDE_VALIDATION", "true");
		context.setInitParameter("javax.faces.PROJECT_STAGE", "Development");
		context.setInitParameter("primefaces.THEME", "none");
	}

	@Bean
	public ServletRegistrationBean facesServlet() {
		FacesServlet servlet = new FacesServlet();
		ServletRegistrationBean registration = new ServletRegistrationBean(servlet, "*.xhtml");
		registration.setName("FacesServlet");
		registration.setLoadOnStartup(1);
		return registration;
	}

	@Bean
	protected WebMvcConfigurerAdapter addViewControllers() {
		return new WebMvcConfigurerAdapter() {

			@Override
			public void addViewControllers(ViewControllerRegistry registry) {
				super.addViewControllers(registry);
				registry.addViewController("/").setViewName("index.xhtml");
			}
		};
	}

	@Bean
	public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
		return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
	}

	@Override
	public void setServletContext(ServletContext servletContext) {
		servletContext.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString());
	}
}
