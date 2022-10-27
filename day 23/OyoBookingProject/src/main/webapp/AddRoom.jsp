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
	<jsp:useBean id="beanDao" class="com.infinite.OyoBookingProject.RoomDAO"/>
<form method="get" action="AddRoom.jsp">
	<center>
		Enter Type :
		<input type="text" name ="type"/><br/><br/>
		Enter CostPerDay :
		<input type="number" name ="costPerDay"/><br/><br/>
		<input type="submit" value ="Insert"/><br/><br/>
	</center>
<c:if test="${param.costPerDay != null && param.type != null}">
	<jsp:useBean id="beanRoom" class="com.infinite.OyoBookingProject.Room" />
	<jsp:setProperty property="*" name="beanRoom"/>
	<c:out value="${beanDao.addRoom(beanRoom)}"/>
</c:if>
</form>
<form action="Booking.jsp">
	Available Room :
	<select name ="RoomStatus">
	<c:forEach var= "room" items="${beanDao.showAvailableRooms()}">
		<option var ="roomId" value="${room}">${room}</option>
	</c:forEach>
	</select>
	<input type="submit" value="Submit"/>
</form>
</body>
</html>