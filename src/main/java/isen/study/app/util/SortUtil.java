package isen.study.app.util;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * isen.study.app.util Created by Pierre-Alexandre Adamski on 18/01/2016.
 */
public class SortUtil {

	/**
	 * found that useful "sort by value" on the web as open source code.
	 */
	public static class SortMapByValueNumber {
		public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue( Map<K, V> map )
		{
			Map<K,V> result = new LinkedHashMap<>();
			Stream<Map.Entry<K,V>> st = map.entrySet().stream();

			st.sorted(Comparator.comparing(Map.Entry::getValue)).forEachOrdered(e ->result.put(e.getKey(),e.getValue()));

			return result;
		}
	}

}
