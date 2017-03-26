package io.lcs.framework.base;

import com.google.gson.Gson;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.beans.PropertyEditorSupport;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by lcs on 15-5-23.
 * BaseController
 */
public class BaseController extends Base {

	@Autowired(required = false)
	protected HttpServletRequest request;
	@Autowired(required = false)
	protected HttpSession session;
	@Autowired(required = false)
	protected Gson gson;

	@Value("${file.download.host}")
	protected String fileHost;


	private final static SimpleDateFormat[] SF = new SimpleDateFormat[]{
			new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"),
			new SimpleDateFormat("yyyy-MM-dd")
	};

	@InitBinder
	public void InitBinder(WebDataBinder dataBinder) {
		dataBinder.registerCustomEditor(String.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				if (value == null) {
					return;
				}
				value = StringEscapeUtils.escapeHtml4(value);
				setValue(value.trim());
			}

		});

		dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
			public void setAsText(String value) {
				for (SimpleDateFormat format : SF) {
					try {
						setValue(format.parse(value));
						return;
					} catch (Exception e) {
					}
				}
				setValue(null);
			}

			public String getAsText() {
				return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format((Date) getValue());
			}

		});
	}

	//@ExceptionHandler(IllegalArgumentException.class)
	public Map hasException(IllegalArgumentException e) {
		request.setAttribute("msg", e.getMessage());
		throw e;
	}

	/**
	 * 重定向
	 *
	 * @param path
	 * @return
	 */
	protected String redirect(String path) {

		String contextPath = this.request.getContextPath();
		if (path.toLowerCase().startsWith("http://") || path.toLowerCase().startsWith("https://")) {
			contextPath = "";
		}
		return F("redirect:%s%s", contextPath, path);
	}
}
