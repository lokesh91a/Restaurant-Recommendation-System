package com.kpt.foodie;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ReviewItem {
	
	String sentiment;
	String text;
	
	public ReviewItem () {
		
	}
	
	public ReviewItem(String text, String sentiment) {
		super();
		this.sentiment = sentiment;
		this.text = text;
	}
	
	public String getSentiment() {
		return sentiment;
	}
	public void setSentiment(String sentiment) {
		this.sentiment = sentiment;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
}
