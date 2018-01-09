package com.dao;

public class couponrankVO {

	String coupon1,coupon2,coupon3,coupon4,coupon5,coupon6;

	public couponrankVO(String coupon1, String coupon2, String coupon3, String coupon4, String coupon5,
			String coupon6) {
		super();
		this.coupon1 = coupon1;
		this.coupon2 = coupon2;
		this.coupon3 = coupon3;
		this.coupon4 = coupon4;
		this.coupon5 = coupon5;
		this.coupon6 = coupon6;
	}

	public String getCoupon1() {
		return coupon1;
	}

	public String getCoupon2() {
		return coupon2;
	}

	public String getCoupon3() {
		return coupon3;
	}

	public String getCoupon4() {
		return coupon4;
	}

	public String getCoupon5() {
		return coupon5;
	}

	public String getCoupon6() {
		return coupon6;
	}
}
