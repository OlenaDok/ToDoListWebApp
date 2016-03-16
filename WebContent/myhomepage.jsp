<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="userlist" uri="/WEB-INF/tags/ListOutput.tld"%>
<!DOCTYPE html  >
<html>
<head>
	  <title>ToDoList: My Home Page</title>
	  <meta charset="utf-8">
	  <meta name="viewport" content="width=device-width, initial-scale=1">
	  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
	  <!--link rel="stylesheet" type="text/css" href="Bootstrap/css/des.css" /-->
	  <link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">
	  <link href='http://fonts.googleapis.com/css?family=Magra|Titan+One' rel='stylesheet' type='text/css' />
	  
</head>
<body>
<%
	long userID = (long) session.getAttribute("userID");
	String userName = (String) session.getAttribute("userName");

	if (userName != null) {
%>

<div align="right">
<%if (userID != 0){%>
	<form action="/ToDoListWebApp/controller/logout" method="get">
    	<button class="btn btn-ls btn-succsess"><i class="glyphicon glyphicon-log-out 1x" style="black;"></i> LogOut</button>
	</form>
		<%}%>
</div>
	<%}%>
<h2 align="center"><%=userName.toUpperCase()%> this is your ToDoList!</h2>
<%
	if (request.getAttribute("userMessage") != null) {
		String userMessage = String.valueOf(request.getAttribute("userMessage"));
%>
	<p><h4><%=userMessage%></h4>
<%}%>
<br />
		
<div class="container col-sm-offset-1 col-sm-11">
	<userlist:list userID="<%=userID%>"></userlist:list>
</div>
	
<form action="/ToDoListWebApp/additem.jsp" method="get">
 <div align="center" >
 	<br /> 
    	<button type="submit" class="btn btn-lg btn-success" >
    	<span class="fa fa-plus fa-1x" style="color:green;"></span> Add New Item</button>
	<br /><br /> 
	</div>
</form>		

</body>
</html>