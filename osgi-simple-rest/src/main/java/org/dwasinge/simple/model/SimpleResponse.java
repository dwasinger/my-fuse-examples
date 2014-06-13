package org.dwasinge.simple.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class SimpleResponse {

	private String reponse;

	public String getReponse() {
		return reponse;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

}
