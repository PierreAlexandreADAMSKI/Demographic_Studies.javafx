package isen.study.data.stat;

import isen.study.app.util.SortUtil;
import isen.study.data.stat.interfaces.Stat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class AverageAgeByState implements Stat {
	public static final String TITLE = "Average Age By State : that lists each state with its average age of the population";

	/**
	 * change getter to QUERY_1 or QUERY_2
	 */
	private static final String QUERY_1 = "SELECT state, " +
			"(YEAR(CURRENT_TIMESTAMP) - YEAR(dateofbirth)) " +
			"AS age FROM person ORDER BY state";
	private static final String QUERY_2 ="SELECT state, " +
			"AVG(YEAR(CURRENT_TIMESTAMP) - YEAR(dateofbirth) - (RIGHT(CURRENT_TIMESTAMP, 5) < RIGHT(dateofbirth, 5))) " +
			"AS age FROM person GROUP BY state  ORDER BY age DESC";

	public static Map<String, Float> averageAgeByState = new HashMap<>();

	@Override
	public Statistics getType() {
		return Statistics.AVERAGE_BY;
	}

	@Override
	public String getDescription() {
		String description = TITLE + "=====";
		Set s = averageAgeByState.entrySet();
		for (Object value1 : s) {
			Map.Entry entry = (Map.Entry) value1;
			String key = (String) entry.getKey();
			Float value = (Float) entry.getValue();
			description += "\n" + key + " => " + value.toString();
		}
		return description;
	}

	@Override
	public String getQuery() {
		return QUERY_2;
	}

	@Override
	public void handle(ResultSet resultSet) throws SQLException {
		/**
		 * USE WITH QUERY_1
		 */
		/*
		int nAge = 0;
		int ageSum = 0;
		if(resultSet.next()) {
			final String comparedState = resultSet.getString("state");
			do {
				final String actualState = resultSet.getString("state");
				final Float age = resultSet.getFloat("age");
				nAge++;
				ageSum += age;
				if (!Objects.equals(comparedState, actualState)){
					final Float average = (float) ageSum/nAge;
					averageAgeByState.put(comparedState, average);
					handle(resultSet);
				}
			}
			while (resultSet.next());
		}
		*/

		/**
		 * USE WITH QUERY_2
		 */
		while (resultSet.next()){
			final String state = resultSet.getString("state");
			final Float age = resultSet.getFloat("age");
			averageAgeByState.put(state, age);
		}
		averageAgeByState = SortUtil.MapSort.sortByValue(averageAgeByState);
	}
}
