<%@ page language="java" contentType="text/html; charset=utf8"
         pageEncoding="utf8" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title><spring:message code="label.title"/></title>
  <link href="resources/login/css/styles.css" rel="stylesheet" type="text/css"/>
</head>
<body onload='document.f.j_username.focus();'>
<div class="panel">

  <div class="panel-tl">
    <div class="panel-tr">
      <div class="panel-tc"></div>
    </div>
  </div>
  <div class="panel-wr"><img src="resources/login/images/gradient.png" class="background"/>
    <% String errorCode = request.getParameter("error");
      String divStyle = errorCode != null ? "style=\"height: 170px;\"" : ""; %>
    <div class="panel-content" <%=divStyle%>>
      <h1><spring:message code="label.input"/></h1>
      <% if (errorCode != null) { %>
      <font color="red"> <spring:message code="label.login_error"/>
        : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message} </font>
      <% } %>

      <form action='<%=request.getContextPath()%>/j_spring_security_check' method="post" name="f">
        <div class="formLine">
          <div><label class="text" for="login"><spring:message code="label.login"/></label></div>
          <div class="leftcp">
            <div class="rightcp">
              <div class="fillinput">
                <input type="text" id="login" name="j_username" class="smoothedge" />
              </div>
            </div>
          </div>
        </div>

        <div class="formLine">
          <label class="text" for="password"><spring:message code="label.password"/></label>
          <div class="leftcp">
            <div class="rightcp"><div class="fillinput"><input type="password" id="password" name="j_password" class="smoothedge" />
            </div>
            </div>
          </div>
        </div>

        <div class="formLine">
          <input name="_spring_security_remember_me" type="checkbox" value="" class="autcheck" /><span class="text"><spring:message code="label.remember"/></span>
        </div>

        <div class="formLine" id="submit">
          <input value="Войти" src="resources/login/images/enter_button.png" type="image" onclick="document.f.submit();" />
        </div>
      </form>
    </div>
  </div>

  <div class="panel-bl">
    <div class="panel-br">
      <div class="panel-bc"> </div>
    </div>
  </div>

</div>
</body>
</html>