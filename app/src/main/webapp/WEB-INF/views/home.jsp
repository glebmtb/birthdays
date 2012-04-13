<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Home</title>
</head>
<body>
<h1>
  Hello world!
</h1>
<a href="<c:url value="/send" />">
  <spring:message code="label.sending" />
</a>
<P>  The time on the server is ${serverTime}. </P>

<P>  Сообщение с сервиса: "${serverMessage}". </P>

<P>Ид: "${id}", Пользователь: ${people} </P>
</body>
</html>
