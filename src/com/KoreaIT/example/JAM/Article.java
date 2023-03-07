package com.KoreaIT.example.JAM;

public class Article {

	int id;
	String body;
	String title;
	
	public  Article(int id,String body,String title) {
		this.body =body;
		this.id =id;
		this.title =title;
		
	}

	@Override
	public String toString() {
		return "Article [id=" + id + ", body=" + body + ", title=" + title + "]";
	}
}
