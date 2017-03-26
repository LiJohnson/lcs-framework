package io.lcs.framework.vo;

import io.lcs.framework.base.Base;
import org.springframework.data.domain.PageRequest;

/**
 * 分页
 * Created by lcs on 15/10/5.
 */
public class PageVo extends Base {
	/**
	 * 页码
	 */
	private int page = 0;
	/**
	 * 大小
	 */
	private int pageSize = 10;


	public int getPage() {
		return page;
	}

	public PageVo setPage(int page) {
		this.page = page;
		return this;
	}

	public int getPageSize() {
		return pageSize;
	}

	public PageVo setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public PageRequest getPageRequest() {
		return new PageRequest(this.page < 1 ? 0 : this.page - 1, this.pageSize);
	}
}