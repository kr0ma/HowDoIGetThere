package be.kroma.web;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import be.kroma.dao.CreateDAOBeans;
import be.kroma.datasource.CreateDataSourceBean;
import be.kroma.security.CreateSecurityFilter;
import be.kroma.services.CreateServiceBeans;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {CreateDAOBeans.class, CreateServiceBeans.class, CreateDataSourceBean.class, CreateSecurityFilter.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { CreateControllerBeans.class };
	}

	@Override
	protected Filter[] getServletFilters() {
		return new Filter[] {};
	}
}