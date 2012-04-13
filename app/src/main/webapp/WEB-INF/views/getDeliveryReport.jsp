<%@ page language="java" contentType="text/html; charset=Cp1251"
         pageEncoding="Cp1251"
         import="java.util.*, java.io.*, java.net.*"  %>
<%
	// ����� ��� ������� � ��������� Giper.mobi
	String login = "test";

	// ������ ��� ������� � ��������� Giper.mobi
	String password = "test";
	 
	// ID ����������, ������� ������������� ��� �������� ���
	String transactionId = "12345";
	
	// ���� ������ - XML-������ � ��������� Giper.mobi
	String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"+
		"<dr>"+		
		"<login>" + login + "</login>"+
		"<pwd>" + password + "</pwd>"+
		"<id>" + transactionId + "</id>"+
		// "<phone>79161234567</phone>"+		// ���� �������� ������������� ����� �� ���������� ���������, �� ����� ��������� ����� ��� ������ ����������� ��������
		"</dr>";	
		
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>��������� ��������� ������ � �������� ������������� ��� ����� ���� Giper.mobi</title>
</head>
<body>
<h1>��������� ��������� ������ � �������� ������������� ��� ����� ���� Giper.mobi</h1>
<hr>
<h4>������������� XML-������:</h4>
	<code>
		<%= xml.replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&gt;&lt;", "&gt;<br>&lt;") %>
	</code>
	
<h4>���������:</h4>
<%
	// ������� �������� ���
	
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
    
    	// ����� ������� Giper.mobi
    	String responceXML = inString.toString().replaceAll("<", "&lt;").replaceAll(">", "&gt;").replaceAll("&gt;&lt;", "&gt;<br>&lt;");
    	%>
    	
    	HTTP-���������� � �������� Giper.mobi ����������.<br>
    	����� �������:<br><br>
    	<code><%= responceXML %></code>
    	
    	<%    	
	} catch (Exception ex) {
    	ex.printStackTrace();
    	%>
    	�� ����� ���������� ��������� ������: <%=ex.getMessage() %>
    	<br>
    	�������� ��� ������ WEB-������� ��� ������� ����������.
    	<%
	}
%>

</body>
</html>