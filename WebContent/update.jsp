<%@page import="il.ac.hit.todolistwebapp.dao.NodeDAO"%>
<%@page import="il.ac.hit.todolistwebapp.models.*"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>ToDoList: Update Item page</title>
	 <link href="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/build/css/bootstrap-datetimepicker.css" rel="stylesheet">
	<link rel="stylesheet" href="Bootstrap/css/bootstrap.min.css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="Bootstrap/css/des.css" />
	<link rel="stylesheet" href="Bootstrap/css/bootstrap-datetimepicker.min.css" />
	<script src="https://code.jquery.com/jquery.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	<link href='http://fonts.googleapis.com/css?family=Magra|Titan+One' rel='stylesheet' type='text/css' />
	<script type="text/javascript" src="Bootstrap/js/bootstrap-datetimepicker.min.js"></script>
	<!-- script type="text/javascript" src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script-->
	<script type="text/javascript" src="Bootstrap/js/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="Bootstrap/js/moment-with-locales.min.js"></script>
	<script type="text/javascript" src="Bootstrap/js/myfunc.js"></script>
	<script src="//cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/e8bddc60e73c1ec2475f827be36e1957af72e2ea/src/js/bootstrap-datetimepicker.js"></script>

<style>
body {
	font-family: 'Titan One';
	font-size: 18px;
	color: black;
	background-color: silver;
}
div{
	
	font-size: 15px;
	color: black;
	background-color: highlight;
}

</style>
<script type="text/javascript">
  $(function() {
    $('#datetimepicker1').datetimepicker({
    	format: 'dd/MM/yyyy hh:mm',
        language: 'pt-BR'
    });
  });
</script>
</head>
<body>
<%
	long userID = (long) session.getAttribute("userID");
	String userName = (String) session.getAttribute("userName");
	//long itemID = (long) session.getAttribute("itemID");
	String title = (String)session.getAttribute("itemTitle");
	String description = (String)session.getAttribute("itemDescription");
	String category = (String)session.getAttribute("category");
	String deadline = (String)session.getAttribute("deadline");
	String isDone = (String) session.getAttribute("isDone");
	System.out.println(userName +" " + title+" " +description);

	if (userName != null)
	{
%>
<h2 class="col-sm-offset-2"><%=userName.toUpperCase()%>!</h2>
<%
	}
%>
<%
	if (request.getAttribute("userMessage") != null)
	{
		String userMessage = String.valueOf(request.getAttribute("userMessage"));
%>
<h4><%=userMessage%></h4>
<%
	}
%>
<h4 class="col-sm-offset-2">Please fill follow fields for update item:</h4>

<div class="col-sm-offset-1 col-sm-6">
<form role="form" action="/ToDoListWebApp/controller/updating" method="get" class="form-horizontal">
	
	<div class="form-horizontal" align="justify">
		
	<div class="col-sm-offset-1 form-group">
		<label class="col-sm-2 control-label">Title</label>
		<div class="col-sm-offset-2 col-sm-8">
			<input class="form-control" name="title" type="hidden" value="<%=title%>">
				<em><label><%=title.toUpperCase()%></label></em>
		</div>
	</div>

	<div class="col-sm-offset-1 form-group">
		<label for="inputTitle" class="col-sm-2 control-label">Description</label>
		 <div class="col-sm-offset-2 col-sm-8">
			<input class="form-control" name="description" type="text" value="<%=description%>">
		</div>
	</div>
	<div class="dropup col-sm-offset-1 form-group">
		<label for="inputCategory" class="col-sm-2 control-label">Category</label>
			<select name="category" class="col-sm-offset-4 col-sm-4">
				<option>STUDY</option>
				<option>WORK</option>
				<option>HOME</option>
				<option>BIRTHDAY</option>
				<option>HEALTH</option>
				<option>PAYMENT</option>
				<option>HOLYDAYS</option>
				<option>BUSINESS</option>
				<option>PRIVATE</option>
			</select>
	</div>
	
	
	<div class="col-sm-offset-1 form-group">
		   <label for="inputDeadLine" class="col-sm-2 control-label">  Deadline</label>
			<div class="row">
				<div class="col-sm-7 btn-ms" >
				            <div class="form-group">
				                <div class='input-group date input-sm col-sm-offset-4' id='datetimepicker1'>
				                    <input type="text" class="form-control" name="deadline" required="required"/>
				                    <span class="input-group-addon">
				                        <span class="glyphicon glyphicon-calendar"></span>
				                    </span>
				                </div>
				            </div>
				        </div>
			</div>
	</div>
	<div class="col-sm-offset-1 form-group" >
		<label for="inputTitle" class="col-sm-1 control-label">IsDone</label>
		<div class="col-sm-offset-1 col-sm-6">
			<select name="isDone" class="col-sm-offset-8 col-sm-8">
				<option>false</option>
				<option>true</option>
			</select>
		</div>
	</div>
	
	<div class="col-sm-offset-1 form-group" >
	   <div class="col-sm-offset-4 col-sm-5">
	      <button type="submit" class="btn btn-lg btn-success btn-block">Update</button>
	   </div>
	 </div>
	
	 <%System.err.println("update.jsp " + session.getAttribute("itemID")); %>
	 <input type="hidden" name="userID" value=<%=session.getAttribute("userID")%> />
	 <input type="hidden" name="itemID" value=<%=session.getAttribute("itemID")%> />
	
	 </div>
	 
</form>
</div>
</body>
</html>
				