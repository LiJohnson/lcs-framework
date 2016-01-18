package io.lcs.framework.base;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * Created by lcs on 9/7/15.
 */
@NoRepositoryBean
public interface BaseRepository<T extends BasePojo> extends JpaRepository<T, Long> , JpaSpecificationExecutor<T> {

}
