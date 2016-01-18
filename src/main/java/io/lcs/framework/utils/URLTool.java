package io.lcs.framework.utils;

import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * Created by lcs on 15-5-23.
 */
public class URLTool {
	public static String prase( String url ){

		return url;
	}

	public static String requestURL(HttpServletRequest request) throws UnsupportedEncodingException {
		if (StringUtils.isBlank(request.getQueryString())) {
			return request.getRequestURL().toString();
		}
		return request.getRequestURL() + "?" + URLDecoder.decode(request.getQueryString(), "UTF-8");
	}

}
