package isen.study.data.stat;

import isen.study.app.util.SortUtil;
import isen.study.data.stat.interfaces.Stat;
import javafx.scene.chart.XYChart;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import isen.study.data.stat.interfaces.Stat;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Pierre-Alexandre Adamski and Maroin Al Dandachi.
 */
public class CommonLastnamesByState implements Stat {

	public static final String TITLE = "Common lastnames by state : that lists each state with its most common lastnames of the population";

	private static final String QUERY = "SELECT lastname, state, count(id)as total FROM person GROUP BY " +
			"state,lastname HAVING total > 2 ORDER BY total DESC, state";

	public static Map<String, Map<String, Integer>> commonLastnamesByState = new HashMap<>();

	@Override
	public Statistics getType() {
		return Statistics.COMMON_BY;
	}

	/**
	 * @return
	 */
	@Override
	public String getDescription() {
		String description = TITLE + "=====";
		Set<Map.Entry<String, Map<String, Integer>>> statesSet = commonLastnamesByState.entrySet();
		for (Map.Entry<String, Map<String, Integer>> entryStates : statesSet) {
			final String states = entryStates.getKey();
			description += "\n=>" + states + " :";
			Set<Map.Entry<String, Integer>> subSet = entryStates.getValue().entrySet();
			for (Map.Entry<String, Integer> entry : subSet) {
				final String lastname = entry.getKey();
				final Integer total = entry.getValue();
				description += "\n" + lastname + " => " + total.toString();
			}
		}
		return description;
	}

	/**
	 * @return
	 */
	@Override
	public String getQuery() {
		return QUERY;
	}

	/**
	 * @param resultSet
	 * @throws SQLException
	 */
	@Override
	public void handle(ResultSet resultSet) throws SQLException {
		Map<String, Integer> tempMap = new HashMap<>();
		while (resultSet.next()) {
			final String state = resultSet.getString("state");
			final String lastname = resultSet.getString("lastname");
			final Integer total = resultSet.getInt("total");
			if(commonLastnamesByState.containsKey(state)) {
				commonLastnamesByState.get(state).put(lastname, total);
			}else {
				//just to initialize the sub-Map of lastname and total
				tempMap.put(lastname,total);
				commonLastnamesByState.putIfAbsent(state, new HashMap<>(tempMap));
				tempMap.clear();
			}
		}
		Set<Map.Entry<String, Map<String, Integer>>> statesSet = commonLastnamesByState.entrySet();
		for (Map.Entry<String, Map<String, Integer>> entryStates : statesSet) {
			final String state = entryStates.getKey();
			commonLastnamesByState.replace(state,SortUtil.SortMapByValueNumber.sortByValue(entryStates.getValue()));
		}
	}
}
