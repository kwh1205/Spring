package com.oracle.oBootMybatis01.configuration;


import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.context.annotation.Configuration;

@Configuration
public class StringConfig {
	private final EntityManager em;
	private final DataSource dataSource;
	
	public StringConfig(EntityManager em, DataSource dataSource) {
		this.em = em;
		this.dataSource = dataSource;
	}
	
	
}
