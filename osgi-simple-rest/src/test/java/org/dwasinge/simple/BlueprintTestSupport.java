package org.dwasinge.simple;

import java.io.IOException;
import java.util.Dictionary;

import org.apache.camel.test.AvailablePortFinder;
import org.apache.camel.test.blueprint.CamelBlueprintTestSupport;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.osgi.service.cm.Configuration;
import org.osgi.service.cm.ConfigurationAdmin;

public abstract class BlueprintTestSupport extends CamelBlueprintTestSupport {

	// this is the OSGI PID used to get a handle on the properties in the OSGi
	// ConfigAdmin service
	public static final String TEST_PID = "org.dwasinge.api";

	private static final Logger log = Logger.getLogger(BlueprintTestSupport.class);

	private Configuration config;

	/**
	 * Returns the key/value properties for this specific PID from the
	 * ConfigAdmin service
	 * 
	 * @return
	 * @throws java.io.IOException
	 */
	protected Dictionary getConfigProperties() throws IOException {

		if (this.config == null) {
			ConfigurationAdmin configurationAdmin = getOsgiService(ConfigurationAdmin.class);
			Assert.assertNotNull(configurationAdmin);

			this.config = configurationAdmin.getConfiguration(TEST_PID);
		}

		return this.config.getProperties();
	}

	@Override
	protected String getBundleFilter() {
		return "(!(Bundle-SymbolicName=org.dwasinge.osgi-simple-rest))";
	}

	/**
	 * Gets the dynamic port that was originally overwritten when this test
	 * starts. See
	 * {@link #useOverridePropertiesWithConfigAdmin(java.util.Dictionary)} for
	 * where we override the props
	 * 
	 * @return
	 */
	public int getPort() {
		Integer cxfPort = null;
		try {
			cxfPort = (Integer) getConfigProperties().get(
					"org.dwasinge.api.http.port");
			log.info("cxfrs port: " + cxfPort);
			Assert.assertNotNull(cxfPort);
			return cxfPort;
		} catch (IOException e) {
			Assert.fail("could not get cxfrs port from config admin");
			return 0;
		}
	}

	/**
	 * Specify which blueprint.xml descriptor file to use
	 * 
	 * @return
	 */
	@Override
	protected String getBlueprintDescriptor() {
		return "OSGI-INF/blueprint/blueprint.xml";
	}

	/**
	 * If you have external properties, put them here in the file defined here.
	 * Make sure you note the convention here... you return an array with two
	 * elements: [0] the file location [1] the ConfigAdmin PID to assign these
	 * props
	 * 
	 * @return
	 */
	@Override
	protected String[] loadConfigAdminConfigurationFile() {
		// which .cfg file to use, and the name of the persistence-id
		// this is a work around when relative path does not load the properties
		// file this workaround may limit the ability to use a supplied prop
		// file in src/main
		String propFileName = this.getClass().getClassLoader()
				.getResource("org.dwasinge.api.properties").getFile();

		return new String[] { propFileName, TEST_PID };
	}

	/**
	 * If you wish to override any properties, specify them here. The contract
	 * requires you to return the PID to which to assign these overrides
	 * 
	 * @param props
	 * @return
	 * @throws Exception
	 */
	@Override
	protected String useOverridePropertiesWithConfigAdmin(Dictionary props)
			throws Exception {
		// override / add extra properties
		// use a port, find the next available starting at port specified in
		// test prop file
		String defaultPort = props.get("org.dwasinge.api.http.port").toString();
		props.put("org.dwasinge.api.http.port", AvailablePortFinder
				.getNextAvailable(Integer.parseInt(defaultPort)));

		// return the persistence-id to use
		return TEST_PID;
	}

	/**
	 * Make sure the clear the config element which is used to store the
	 * Configuration from ConfigAdmin
	 * 
	 * @throws Exception
	 */
	@Override
	public void tearDown() throws Exception {
		super.tearDown();
		this.config = null;
	}

}
