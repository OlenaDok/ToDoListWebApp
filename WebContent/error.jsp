<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	 <title>ToDoList: Error page</title>
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
	 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	 <link rel="stylesheet" type="text/css" href="../css/button.css" />
	 
	 <link href='http://fonts.googleapis.com/css?family=Magra|Titan+One' rel='stylesheet' type='text/css' />
	 <link href="/bootstrap/css/bootstrap.min.css" rel="stylesheet">
     <script src="/scripts/jquery.min.js"></script>
   	 <script src="/bootstrap/js/bootstrap.min.js"></script>
	 
	<style>
	body{
		font-family: 'Titan One';
		font-size: 52px;
	}
	</style>

</head>
<body>
<p align="center"> Error!</p>
<%
	String userMessage = "";
	if(request.getAttribute("userMessage")!=null)
	{
		userMessage = (String)request.getAttribute("userMessage");
	}
	%>
	<div class = "alert alert-danger">
   	<a href = "#" class = "close" data-dismiss = "alert">
      &times;
   	</a>
   <div align="center">
	<%
		String message = (String)request.getAttribute("userMessage");
		if (message != null)
		{
	%>
	<strong> <%=message%>.</strong>
	<%
		}
	%>
	</div>
	
</div>
	<div align="center">
  	<form action="/ToDoListWebApp/controller/home" method="get" >
			<input type="submit" class="btn" value="Return to home page" />
	</form>
	</div>
</body>
</html>