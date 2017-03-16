package io.lcs.framework.base;

import io.lcs.framework.annotation.Comment;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by lcs on 9/3/15.
 */
@MappedSuperclass
public class BasePojo extends BaseBean implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Comment("id")
	protected long id;

	@Column(nullable = false)
	@Version
	@Comment("version")
	protected int version;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Comment("创建时间")
	private Date createTime;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
