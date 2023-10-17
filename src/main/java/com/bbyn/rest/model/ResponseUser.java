package com.mkyong.rest;

public class ResponseSMS {

	String status;
	int statusCode;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	

	@Override
	public String toString() {
		return "{\"status\": "+ status + ", 	\"statusCode\": "+ statusCode + "}";
	}
}