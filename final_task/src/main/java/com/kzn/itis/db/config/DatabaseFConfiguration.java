package com.kzn.itis.db.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
public class DatabaseFConfiguration {

	@Value("${db.url}")
	private String dbUrl;

	public String getDbUrl() {
		return dbUrl;
	}
	public void setDbURL(String url){
		this.dbUrl = url;
	}
}
