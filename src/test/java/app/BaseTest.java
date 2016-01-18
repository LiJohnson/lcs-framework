package app;

import io.lcs.framework.base.Base;

/**
 * Created by lcs on 10/19/15.
 *
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@TestPropertySource(locations = {
		"file:src/test/resources/application-test.properties",
		"file:src/test/resources/application-config.properties",
		"file:src/main/resources/application.properties",
		"file:src/main/resources/application-sms.properties"})
*/
public class BaseTest extends Base{
	//protected final Logger LOG = LoggerFactory.getLogger(this.getClass());
}
