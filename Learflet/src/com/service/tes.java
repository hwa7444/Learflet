package com.service;

class tes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String s;
		    String[] array;


		    // 콜론(:)으로 구분된 문자열 분해
		    s = "자장면:탕수육:짬뽕:칼국수";
		    array = s.split(":");
		 for (int i = 0; i < array.length; i++) {
		      System.out.format("array[%d] = %s%n", i, array[i]);
		 	System.out.println("결과값:"+array[i]);
		 }

	}

}
