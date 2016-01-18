package io.lcs.framework.base;

import io.lcs.framework.annotation.Comment;

import javax.persistence.*;
import java.io.Serializable;

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
	@Comment("version")
	protected int version;

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

}
