<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	 <title>ToDoList: Create New User page</title>
	 <meta name="viewport" content="width=device-width, initial-scale=1.0">
	 <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
	 <link rel="stylesheet" type="text/css" href="../css/button.css" />
	 <link rel="stylesheet" type="text/css" href="Bootstrap/css/des.css" />
	 <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	 <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
</head>
<body>
<%if(request.getAttribute("userMessage")!=null)
	{
		out.print(request.getAttribute("userMessage"));
	}
%>

<div class="container">
  <h2 class="col-sm-offset-2">Create new user</h2>
  <form action="/ToDoListWebApp/controller/newuser" method="get" class="form-horizontal" role="form">
    <div class="form-group">
      <label class="control-label col-sm-2" for="email">User Name:</label>
      <div class="col-sm-5">
        <input class="form-control" id="username" name="username" placeholder="Your user name" required autofocus>
      </div>
    </div>
    <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">E-Mail:</label>
      <div class="col-sm-5">          
        <input class="form-control" type="email" id="email" name="email" placeholder="Your e-mail" required autofocus>
      </div>
    </div>
     <div class="form-group">
      <label class="control-label col-sm-2" for="pwd">Password:</label>
      <div class="col-sm-5">          
        <input class="form-control" type="password" id="password" name="password" placeholder="Password" required="required">
      </div>
    </div>
    <div class="form-group">        
      <div class="col-sm-offset-2 col-sm-5">
       <button type="submit" class="btn btn-lg btn-success btn-block">Log in</button>
      </div>
    </div>
  </form>
</div>
	
</body>
</html>