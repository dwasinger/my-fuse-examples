package org.dwasinge.provider;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.SerializationConfig;

public class JacksonJsonProviderFactory {

	public static JacksonJsonProvider createProvider() {
		JacksonJsonProvider provider = new JacksonJsonProvider();
		provider.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, false);
		return provider;
	}

}
