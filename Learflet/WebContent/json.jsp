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

  // 데이터를 안드로이드에서 받음

	String recvMessage = request.getParameter("msg");



  // 초기 선언

	JSONObject jsonMain = new JSONObject();
	JSONObject jsonMain2 = new JSONObject();
	JSONArray jArray = new JSONArray();

	

	JSONObject jObject1 = new JSONObject();

	JSONObject jObject2 = new JSONObject();

	JSONObject jObject3 = new JSONObject();



        // 안드로이드로 보낼 메시지를 만듬

	jObject1.put("msg1", recvMessage);

	jObject2.put("msg2", "메시지2!");

	jObject3.put("msg3", "3번째 메시지!");

	

        // 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬

	jArray.add(0, jObject1);

	jArray.add(0, jObject2);

	jArray.add(0, jObject3);



        // 최종적으로 배열을 하나로 묶음

	jsonMain.put("List", jArray);

	

        // 안드로이드에 보낼 데이터를 출력

	out.println(jsonMain.toJSONString());

	

%>




</body>
</html>