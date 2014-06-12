package org.dwasinge.impl;

import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.dwasinge.SimpleService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class SimpleServiceTest extends CamelBlueprintTestSupport {

	private SimpleService simpleService;

	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}

	@Before
	public void setup() {
		simpleService = getOsgiService(SimpleService.class);
	}

	@After
	public void tearDown() {
		simpleService = null;
	}

	@Test
	public void testEchoMethod() {

		assertNotNull(simpleService);

		final String call = "hello";
		final String expectedResponse = "hello hello";
		String response = simpleService.echo(call);

		assertEquals(expectedResponse, response);

	}

}
