package com.service;

class tes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String s;
		    String[] array;


		    // �ݷ�(:)���� ���е� ���ڿ� ����
		    s = "�����:������:«��:Į����";
		    array = s.split(":");
		 for (int i = 0; i < array.length; i++) {
		      System.out.format("array[%d] = %s%n", i, array[i]);
		 	System.out.println("�����:"+array[i]);
		 }

	}

}
