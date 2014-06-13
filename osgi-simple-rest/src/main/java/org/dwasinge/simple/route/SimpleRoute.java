package org.dwasinge.simple.route;

import org.apache.camel.builder.RouteBuilder;
import org.dwasinge.simple.model.SimpleResponse;

public class SimpleRoute extends RouteBuilder {

	private static final String DEFAULT_FROM_EP = "direct:SimpleResource";
	private static final String DEFAULT_TO_EP = "{{org.dwasinge.api.simpleToEp}}";

	private String fromEp = null;
	private String toEp = null;

	@Override
	public void configure() throws Exception {

		from(getFromEp())
			.routeId("SimpleRestAPI")
			.setBody(method(this, "getResponse"))
		.to(getToEp());

	}

	public SimpleResponse getResponse() {
		SimpleResponse response = new SimpleResponse();
		response.setReponse("hello");
		return response;
	}

	public void setFromEp(String newEp) {
		this.fromEp = newEp;
	}

	public String getFromEp() {
		if (fromEp == null) {
			return DEFAULT_FROM_EP;
		}
		return fromEp;
	}

	public void setToEp(String newEp) {
		this.toEp = newEp;
	}

	public String getToEp() {
		if (toEp == null) {
			return DEFAULT_TO_EP;
		}
		return toEp;
	}

}
