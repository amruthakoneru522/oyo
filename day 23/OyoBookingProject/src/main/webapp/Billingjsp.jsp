<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form action="">
	<center>
		<input type="text" name="roomId" />
		<input type="submit" value="Check Out"></center>
	</form>
	<c:out value="${param.roomId != null }"></c:out>
	<c:if test="${param.roomId != null }">
		<jsp:useBean id="beanDao"  class="com.infinite.OyoBookingProject.RoomDAO" />
		<c:out value="${beanDao.billing(param.roomId)}" />
	</c:if>
	
</body>
</html>