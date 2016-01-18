package io.lcs.framework.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by lcs on 9/3/15.
 */
public class Base {
    protected transient final Logger LOG = LoggerFactory.getLogger(this.getClass());
    protected final static String F( String format ,Object...val ) {
        return String.format(format, val);
    }

    @Value("${setting.is_dev:false}")
    protected transient boolean isDev;
}
