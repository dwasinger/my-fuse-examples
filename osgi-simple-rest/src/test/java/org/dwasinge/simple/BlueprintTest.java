package org.dwasinge.simple;

import org.apache.camel.component.cxf.blueprint.RsServerBlueprintBean;
import org.junit.Test;

public class BlueprintTest extends BlueprintTestSupport {

	@Test
	public void rsServerBeanConfigTest() {
		RsServerBlueprintBean rsServer = (RsServerBlueprintBean) context
				.getRegistry().lookup("restServer");

		assertEquals("Port not set correctly", "http://0.0.0.0:" + getPort()
				+ "/rest/v1/", rsServer.getAddress());

	}

}
