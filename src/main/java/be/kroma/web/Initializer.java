package be.kroma.web;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import be.kroma.datasource.CreateDataSourceBean;
import be.kroma.security.CreateSecurityFilter;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
	@Override
	protected String[] getServletMappings() {
		return new String[] { "/" };
	}

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] {CreateDataSourceBean.class, CreateSecurityFilter.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { CreateControllerBeans.class };
	}

	@Override
	protected Filter[] getServletFilters() {
		// CharacterEncodingFilter utf8Filter = new CharacterEncodingFilter();
		// utf8Filter.setEncoding("UTF-8");
		return new Filter[] {};
	}
}