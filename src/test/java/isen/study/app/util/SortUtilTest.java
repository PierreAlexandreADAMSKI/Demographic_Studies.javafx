package isen.study.app.util;

import java.util.*;
import org.junit.*;

public class SortUtilTest {
	@Test
	public void testSortMapByValueNumber()
	{
		Random random = new Random(System.currentTimeMillis());
		Map<String, Integer> testMap = new HashMap<String, Integer>(1000);
		for(int i = 0 ; i < 1000 ; ++i) {
			testMap.put( "SomeString" + random.nextInt(), random.nextInt());
		}

		testMap = SortUtil.MapSort.sortByValue( testMap );
		Assert.assertEquals( 1000, testMap.size() );

		Integer previous = null;
		for(Map.Entry<String, Integer> entry : testMap.entrySet()) {
			Assert.assertNotNull( entry.getValue() );
			if (previous != null) {
				Assert.assertTrue( entry.getValue() >= previous );
			}
			previous = entry.getValue();
		}
	}

}