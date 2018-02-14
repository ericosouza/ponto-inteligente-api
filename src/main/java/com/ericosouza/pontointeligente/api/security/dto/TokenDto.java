package com.ericosouza.pontointeligente.api.security.dto;

public class TokenDto {

	private String token;

	public TokenDto() {
	}

	public TokenDto(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
