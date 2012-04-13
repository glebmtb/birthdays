<%@ page language="java" contentType="text/html; charset=Cp1251"
         pageEncoding="Cp1251"
         import="java.util.*, java.io.*, java.net.*"  %>
<%
	// Логин для доступа к платформе Giper.mobi
	String login = "test";

	// Пароль для доступа к платформе Giper.mobi
	String password = "test";
	 
	// ID транзакции, который использовался при отправке СМС
	String transactionId = "12345";
	
	// Сама строка - XML-запрос к плафторме Giper.mobi
	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
		"<dr>"+		
		"<login>" + login + "</login>"+
		"<pwd>" + password + "</pwd>"+
		"<id>" + transactionId + "</id>"+
		// "<phone>79161234567</phone>"+		// Если отправка производилась сразу по нескольким телефонам, то можно запросить отчет для одного конкретного телефона
		"</dr>";	
		
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Страничка получения отчета о доставке отправленного СМС через шлюз Giper.mobi</title>
</head>
<body>
<h1>Страничка получения отчета о доставке отправленного СМС через шлюз Giper.mobi</h1>
<hr>
<h4>Отправлояемый XML-запрос:</h4>
	<code>
		<%= xml.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&gt;&lt;", "&gt;<br>&lt;") %>
	</code>
	
<h4>Результат:</h4>
<%
	// Процесс отправки СМС
	
	try {
		String urlStr = "http://giper.mobi/api/dr";
	
		URL url = new URL(urlStr);
    	HttpURLConnection huc = (HttpURLConnection)url.openConnection();
    	huc.setDoOutput(true);
    	huc.setDoInput(true);
    	huc.setRequestMethod("POST");
    	huc.setRequestProperty("Content-Type","application/xml");
    	OutputStream os = huc.getOutputStream();
    
    	os.write(xml.getBytes());
    	os.flush();
    	os.close();
    
    	InputStream is = huc.getInputStream();
    	InputStreamReader inputstreamreader = new InputStreamReader(is);
   		BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
   		StringBuffer inString = new StringBuffer();
   		String s3;
   		while((s3 = bufferedreader.readLine()) != null)
   		{
   			inString.append(s3);
   		}
   		bufferedreader.close();

    	huc.disconnect();
    
    	// Ответ сервера Giper.mobi
    	String responceXML = inString.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&gt;&lt;", "&gt;<br>&lt;");
    	%>
    	
    	HTTP-соединение с сервером Giper.mobi состоялось.<br>
    	Ответ сервера:<br><br>
    	<code><%= responceXML %></code>
    	
    	<%    	
	} catch (Exception ex) {
    	ex.printStackTrace();
    	%>
    	Во время транзакции произошла ошибка: <%=ex.getMessage() %>
    	<br>
    	Смотриле лог своего WEB-сервера для большей информации.
    	<%
	}
%>

</body>
</html>