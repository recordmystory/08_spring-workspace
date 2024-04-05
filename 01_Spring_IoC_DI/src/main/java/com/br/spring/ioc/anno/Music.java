package com.br.spring.ioc.anno;

public class Music {

	private String name;
	private String genre;
	public String title;

	public Music() {
	}

	public Music(String name, String genre) {
		super();
		this.name = name;
		this.genre = genre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Music [name=" + name + ", genre=" + genre + ", title=" + title + "]";
	}

	

}
