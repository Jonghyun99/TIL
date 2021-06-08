<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
 <c:forEach var="i" begin="0" end="10" varStatus="status" step="1">
 ${i}
 ${status.index}
 
 </c:forEach>
</body>
</html>