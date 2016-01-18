package isen.study.data.stat.interfaces;

import isen.study.data.stat.Statistics;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public interface Stat {
	Statistics getType();
	String getDescription();
	String getQuery();
	void handle(ResultSet resultSet) throws SQLException;
}
