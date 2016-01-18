package isen.study.data.stat;

import isen.study.data.stat.interfaces.Stat;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class AverageBy implements Stat {
	@Override
	public Statistics getType() {
		return null;
	}

	@Override
	public String getDescription() {
		return null;
	}

	@Override
	public String getQuery() {
		return null;
	}

	@Override
	public void handle(ResultSet resultSet) throws SQLException {

	}
}
