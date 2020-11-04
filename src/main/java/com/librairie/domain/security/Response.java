package com.librairie.domain.security;

import java.io.Serializable;

public class Response implements Serializable {
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
