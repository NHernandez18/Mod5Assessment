<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Phones :)</title>
</head>
<body>
	
	List of phones:
	<table>
		<c:forEach items="${requestScope.allPhones}" var="currentphone">
			<tr>
			 	<%-- <td><input type="radio" name="id" value="${currentitem.id}"></td>--%>
				 <td>${currentphone.model}</td>
				 <td>${currentphone.os}</td>
			 </tr>
		
		<%-- <c:forEach items="${requestScope.allBooks}" var="currentbook">
			<tr>
				 <td><input type="radio" name="id" value="${currentitem.id}"></td>
				 <td>${currentbook.title}</td>
				 <td>${currentbook.author}</td>
				</tr>
		</c:forEach>--%>
		</c:forEach>
	</table>  
	<br />
	<a href="index.html">Home</a>
	
</body>
</html>