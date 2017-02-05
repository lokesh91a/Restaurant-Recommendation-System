package com.kpt.foodie;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class LocationItem {
	String loc;
	
	public LocationItem() {
		
	}
	
	public LocationItem(String loc) {
		this.loc = loc;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

}
