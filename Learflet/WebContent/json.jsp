<%@page import="org.json.simple.JSONArray"%>
<%@page import="org.json.simple.JSONObject"%>


<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
<%

  // �����͸� �ȵ���̵忡�� ����

	String recvMessage = request.getParameter("msg");



  // �ʱ� ����

	JSONObject jsonMain = new JSONObject();
	JSONObject jsonMain2 = new JSONObject();
	JSONArray jArray = new JSONArray();

	

	JSONObject jObject1 = new JSONObject();

	JSONObject jObject2 = new JSONObject();

	JSONObject jObject3 = new JSONObject();



        // �ȵ���̵�� ���� �޽����� ����

	jObject1.put("msg1", recvMessage);

	jObject2.put("msg2", "�޽���2!");

	jObject3.put("msg3", "3��° �޽���!");

	

        // ������ ���� ������ ��ü�� �ϳ��� �迭 ���·� ����

	jArray.add(0, jObject1);

	jArray.add(0, jObject2);

	jArray.add(0, jObject3);



        // ���������� �迭�� �ϳ��� ����

	jsonMain.put("List", jArray);

	

        // �ȵ���̵忡 ���� �����͸� ���

	out.println(jsonMain.toJSONString());

	

%>




</body>
</html>