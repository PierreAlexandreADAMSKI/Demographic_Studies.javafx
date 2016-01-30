package isen.study.app.util;

import java.util.*;
import java.util.stream.Stream;

/**
 * isen.study.app.util Created by Pierre-Alexandre Adamski on 18/01/2016.
 */
public class SortUtil {

	/**
	 * found that useful "sort by value" on the web as open source code.
	 * using Nested Class let you set a util set of class for nyKing of Sorting in the project
	 * to be continued...
	 */

	public static class MapSort {

		public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
			List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());

			Collections.sort(list, (o1, o2) -> (o1.getValue()).compareTo(o2.getValue()));

			Map<K, V> result = new LinkedHashMap<>();
			for (Map.Entry<K, V> entry : list) {
				result.put(entry.getKey(), entry.getValue());
			}

			return result;
		}

	}
}
