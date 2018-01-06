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
   request.setCharacterEncoding("UTF-8");
    
   String returns = "";
   String type = request.getParameter("type");
   String vision = request.getParameter("vision_write");
 
 
%>
<%
   if (type == null) {
      return;
   }else if (type.equals("vision_write")) {
      System.out.println("값을받았습니다."+vision);
      
    }
%>
</body>
</html>