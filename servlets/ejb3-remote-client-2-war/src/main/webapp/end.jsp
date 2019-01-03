<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Quizzz</title>
</head>

<body>

	<%
		String playerName = "";
		playerName = request.getParameter("playerName");
		
		int score = 0;
		score = Integer.valueOf(request.getParameter("score"));
	%>
	
	Thank you <%=playerName%> for playing :) <br />
	Your score is <%=score%> <br />
	See you sometime again! <br />
	<a href="index.html">Play again!</a>

</body>
</html>