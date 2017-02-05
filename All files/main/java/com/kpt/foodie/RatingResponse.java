package com.kpt.foodie;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class RatingResponse {
	String businessId;
	String name;
	double frating;
	String address;
	
	public RatingResponse() {
		
	}
	
	public RatingResponse(String businessId, String name, String address, Double frating) {
		this.businessId = businessId;
		this.name = name;
		this.address = address;
		this.frating = frating;
	}

	public String getBusinessID() {
		return businessId;
	}

	public void setBusinessID(String business_id) {
		this.businessId = business_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getFrating() {
		return frating;
	}

	public void setFrating(double frating) {
		this.frating = frating;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
