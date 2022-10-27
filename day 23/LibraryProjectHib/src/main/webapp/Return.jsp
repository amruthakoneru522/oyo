<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<jsp:include page="Menu.jsp"/><br/>
	<jsp:useBean id="beanDao" class="com.infinite.LibraryProjectHib.LibraryDAO"/>
	<c:set var="user" value="${sessionScope.user}"/>
	<br/> Select books to issue: <br/> 

	<form action="return.jsp" method="get">
	<table border='3'>
		<tr>
		 <th>TID</th>
		 <th>Book Id </th>
		 <th>Name</th>
		 <th>Return</th>
	    </tr>
	  <c:forEach var="trans" items="${beanDao.issueBooks(user)}">
	  	<tr>
	  		<td><c:out value="${trans.tid }"></c:out></td>
	  		<td><c:out value="${trans.bookId}"/> </td>
	  		<td><c:out value="${trans.userName}"/> </td>
	  		<td><input type="checkbox" name="bid" value="${trans.bookId}" /></td>
	  	</tr>
	  </c:forEach>
	 </table>
	 <br/>
	 <input type='submit' value='Issue Books' >
	</form>

	<c:forEach var="b" items="${paramValues.bid}">
		<c:set var="bid" value="${b}"/>
		<c:set var="user" value="${sessionScope.user}"/>
		<c:out value="${beanDao.returnBookDAO(bid,user)}"/>
	</c:forEach>

</body>
</html>