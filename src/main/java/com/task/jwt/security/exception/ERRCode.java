package com.task.jwt.security.exception;

public enum ERRCode implements ErrorMessage {

	FILE_CURRUPTED("ERR_FILE_0001", "File is corrupted");
	private String code;
	private String description;

	private ERRCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	@Override
	public String getCode() {
		return this.code;
	}

	@Override
	public String getDescription() {
		return this.description;
	}
}
