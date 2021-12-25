package com.example.app.rest.model;

public class SampleResponse {
	private String hoge;
	private Integer fuga;
	private Hoge ho = new Hoge();
	
	public Hoge getHo() {
		return ho;
	}

	public void setHo(Hoge ho) {
		this.ho = ho;
	}

	public Integer getFuga() {
		return fuga;
	}

	public void setFuga(Integer fuga) {
		this.fuga = fuga;
	}

	public String getHoge() {
		return hoge;
	}

	public void setHoge(String hoge) {
		this.hoge = hoge;
	}
	
}
