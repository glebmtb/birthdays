<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
  <title>Home</title>
</head>
<body>
<h1>
  Hello world!
</h1>

<P>  The time on the server is ${serverTime}. </P>

<P>  Сообщение с сервиса: "${serverMessage}". </P>

<P>Ид: "${id}", Пользователь: ${people} </P>
</body>
</html>
