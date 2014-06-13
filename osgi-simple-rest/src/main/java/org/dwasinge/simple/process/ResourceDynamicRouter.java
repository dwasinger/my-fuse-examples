package org.dwasinge.simple.process;

import org.apache.camel.Exchange;
import org.apache.camel.Header;
import org.apache.camel.Property;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.cxf.jaxrs.model.MethodInvocationInfo;
import org.apache.cxf.jaxrs.model.OperationResourceInfoStack;
import org.apache.log4j.Logger;

public class ResourceDynamicRouter {

	private Logger LOG = Logger.getLogger(ResourceDynamicRouter.class);

	public String route(
			@Header(CxfConstants.CAMEL_CXF_RS_OPERATION_RESOURCE_INFO_STACK) OperationResourceInfoStack opInfo,
			@Property(Exchange.SLIP_ENDPOINT) String slip) {

		// Only route if has not been routed already
		if (slip != null) {
			return null;
		}

		// get the first op info on the stack or return null
		if (opInfo == null) {
			return null;
		}

		MethodInvocationInfo info = opInfo.get(0);

		// from the resource class construct a direct:resourceName slip and
		// return
		Class resourceClass = info.getRealClass();
		String name = resourceClass.getSimpleName();

		String route = "direct://" + name;
		LOG.info("routing to "+route);
		return route;
	}

}
