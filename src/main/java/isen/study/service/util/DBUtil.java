package isen.study.service.util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import java.util.Properties;

/**
 * isen.study.service.util Created by Pierre-Alexandre Adamski on 18/01/2016.
 */
public class DBUtil {
	public static MysqlDataSource dbInit(Properties properties){
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setServerName(properties.getProperty("db.server"));
		dataSource.setPort(Integer.valueOf(properties.getProperty("db.port")));
		dataSource.setUser(properties.getProperty("db.username"));
		dataSource.setPassword(properties.getProperty("db.password"));
		dataSource.setDatabaseName(properties.getProperty("db.schema"));
		return dataSource;
	}
}
