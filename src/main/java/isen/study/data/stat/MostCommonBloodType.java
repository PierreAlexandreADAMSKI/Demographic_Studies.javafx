package isen.study.data.stat;

import isen.study.app.util.SortUtil;
import isen.study.data.stat.interfaces.Stat;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class MostCommonBloodType implements Stat {
	private static final String QUERY = "SELECT bloodtype, count(id) " +
			"as total FROM person GROUP BY bloodtype ORDER BY total DESC";

	public static final String TITLE = "Most Common Blood Type : list the blood types of population";

	public static Map<String, Integer> mostCommonBloudType = new HashMap<>();

	@Override
	public Statistics getType() {
		return Statistics.MOST_COMMON;
	}

	@Override
	public String getDescription() {

		String description = TITLE + "=====";
		Set s = mostCommonBloudType.entrySet();
		for (Object value1 : s) {
			Map.Entry entry = (Map.Entry) value1;
			String key = (String) entry.getKey();
			Integer value = (Integer) entry.getValue();
			description += "\n" + key + " => " + value.toString();
		}
		return description;
	}

	@Override
	public String getQuery() {
		return QUERY;
	}

	@Override
	public void handle(ResultSet resultSet) throws SQLException {
		while (resultSet.next()){
			final String bloodType = resultSet.getString("bloodtype");
			final Integer total = resultSet.getInt("total");
			mostCommonBloudType.put(bloodType, total);
		}
		mostCommonBloudType = SortUtil.SortMapByValueNumber.sortByValue(mostCommonBloudType);

	}
}
