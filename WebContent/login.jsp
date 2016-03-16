<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
 <!DOCTYPE html >
<html>
<head>
	 <title>ToDoList: Login page</title>
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
	 <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
	 <link rel="stylesheet" type="text/css" href="Bootstrap/css/des.css" />
	 <link href='http://fonts.googleapis.com/css?family=Magra|Titan+One' rel='stylesheet' type='text/css' />
	 
</head>
<body>
<%if(request.getAttribute("userMessage")!=null)
	{
		out.print(request.getAttribute("userMessage"));
	}
%>
	<form  action="/ToDoListWebApp/controller/login" method="get">
	
	<h2 class="col-sm-offset-2  form-heading ">Please sign in</h2>
	<div class="form-horizontal" align="center">
	<div class="col-sm-offset-1 form-group" >
		<label for="inputName" class="col-sm-2 control-label">Your user name</label>
		<div class="col-sm-offset-1 col-sm-2">
			<input class="form-control" name="username" placeholder="Your user name" required autofocus>
		</div>
	</div>
	<div class="col-sm-offset-2 form-group" >
		<label for="inputPassword" class="col-sm-2 control-label">Your password</label>
		<div class="col-sm-offset-1 col-sm-2">
			<input class="form-control" type="password" name="password" placeholder="Password" required="required">
		</div>
	</div>
	
	 <div class="col-sm-offset-2 form-group" >
	   <div class="col-sm-offset-2 col-sm-2">
	      <button type="submit" class="btn btn-lg btn-success btn-block">Log in</button>
	   </div>
	 </div>
	 </div>
	</form>
	
	<%
	String userMessage = "";
	if(request.getAttribute("userMessage")!=null)
	{
		userMessage = (String)request.getAttribute("userMessage");
	}
	
	%>
</body>
</html>