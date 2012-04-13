<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Страничка отправки SMS используя шлюз Giper.mobi</title>
</head>
<body>
<h1>Страничка отправки SMS используя шлюз Giper.mobi</h1>
<hr>

<form:form method="post" action="send" commandName="sms">

</form:form>

<h4>Отправлояемый XML-запрос:</h4>
  <P>${xml}</P>
<h4>Результат:</h4>
  <P>${response}</P>
</body>
</html>