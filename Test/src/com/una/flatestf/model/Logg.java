package com.una.flatestf.model;

public class Logg {
	private String filename;
	private String filepath;
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Logg(String filename, String filepath) {
		super();
		this.filename = filename;
		this.filepath = filepath;
	}
	
}
