<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Страничка отправки SMS используя шлюз Giper.mobi</title>
</head>
<body>
<h1>Страничка отправки SMS используя шлюз Giper.mobi</h1>
<hr>

<form:form method="post" action="sendSms" commandName="sms">
   <table>
    <tr>
      <td><form:label path="phone">
        <spring:message code="label.phone" />
      </form:label></td>
      <td><form:input path="phone" /></td>
    </tr>
    <tr>
      <td><form:label path="sms_text">
        <spring:message code="label.smsText" />
      </form:label></td>
      <td><form:input path="sms_text" /></td>
    </tr>
    <tr>
      <td><form:label path="transactionId">
        <spring:message code="label.smsId" />
      </form:label></td>
      <td><form:input path="transactionId" /></td>
    </tr>
    <tr>
      <td colspan="2"><input type="submit"
                             value="<spring:message code="label.send"/>" /></td>
    </tr>
  </table>
</form:form>

<h4>Отправлояемый XML-запрос:</h4>
  <P>${xml}</P>
<h4>Результат:</h4>
  <P>${response}</P>
</body>
</html>