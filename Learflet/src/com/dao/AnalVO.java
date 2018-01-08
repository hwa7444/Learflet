package com.dao;

public class AnalVO {


	String id,age,gender,interest1,interest2,interest3;

	public AnalVO(String id, String age, String gender, String interest1, String interest2, String interest3) {
		super();
		this.id = id;
		this.age = age;
		this.gender = gender;
		this.interest1 = interest1;
		this.interest2 = interest2;
		this.interest3 = interest3;
	}

	public String getId() {
		return id;
	}

	public String getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	public String getInterest1() {
		return interest1;
	}

	public String getInterest2() {
		return interest2;
	}

	public String getInterest3() {
		return interest3;
	}
	
	
	
}
