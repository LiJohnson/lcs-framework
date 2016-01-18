package io.lcs.framework.utils;

import io.lcs.framework.base.BasePojo;
import org.springframework.util.Assert;

/**
 * Created by lcs on 12/5/15.
 */
public class AssertExt extends Assert {

	public static void hasId(BasePojo pojo , String message) {
		isTrue(pojo != null && pojo.getId() > 0, message);
	}
}

