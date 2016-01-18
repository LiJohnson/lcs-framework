package io.lcs.framework.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lcs on 15-5-31.
 */
public class SetUtils {
	/**i
	 * a - b
	 * @param a
	 * @param b
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> diffSet(List<T> a, List<T> b) {
		List<T> c = new ArrayList<T>();
		Map<T, T> map = new HashMap<T, T>();
		for (T t : b) {
			map.put(t, t);
		}
		for (T t : a) {
			if( map.get(t) == null ) c.add(t);
		}
		return c;
	}
}
