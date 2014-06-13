package org.dwasinge.provider;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.SerializationConfig;

public class JacksonJsonProviderFactory {

    /**
     * Create v1.9 provider for use with 1.0 MIL implementation
     * See jira MIL-60
     * @return
     */
    public static JacksonJsonProvider createProvider(){
        JacksonJsonProvider provider = new JacksonJsonProvider();
        provider.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, false);
        return provider;
    }

    /**
     * Create fasterxml jackson provider with custom features for MIL
     * @return
     */
    /*public static com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider createProvider() {
        com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider rc = new JacksonJaxbJsonProvider();
        rc.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        return rc;
    }*/

}
