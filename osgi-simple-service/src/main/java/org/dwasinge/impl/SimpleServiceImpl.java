package org.dwasinge.impl;

import org.dwasinge.SimpleService;

public class SimpleServiceImpl implements SimpleService {

	@Override
	public String echo(String value) {
		return value+" "+value;
	}

}
