<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" errorPage=""%>
<!DOCTYPE >
<html>
<head>
<title>ToDoList: Add New Item</title>
<meta name="viewport" http-equiv="Content-Type" content="text/html" content="width=device-width, initial-scale=1.0">

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
}

h4 {
	font-family: 'Titan One';
	font-size: 25px;
	color: white;
}
</style>

<script type="text/javascript">
  $(function() {
    $('#datetimepicker1').datetimepicker({
      language: 'pt-BR'
    });
  });
</script>
</head>
<body>
	<%
		long userID = (long) session.getAttribute("userID");
		String userName = (String) session.getAttribute("userName");

		if (userName != null) {
	%>
	<h2 class="col-sm-offset-2"><%=userName.toUpperCase()%>!
	</h2>
	<%
		}
	%>
	<%
		if (request.getAttribute("userMessage") != null) {
			String userMessage = String.valueOf(request.getAttribute("userMessage"));
	%>
	<h4><%=userMessage%></h4>
	<%
		}
	%>
	<h4 class="col-sm-offset-2">
		Please fill follow fields<br>for adding new item:
	</h4>
	<div class="col-sm-offset-1 col-sm-5">
		<form role="form" action="/ToDoListWebApp/controller/additem" method="get">


			<div class="form-horizontal" align="center">

				<div class="col-sm-offset-1 form-group">
					<label for="inputTitle" class="col-sm-2 control-label">Title</label>
					<div class="col-sm-offset-2 col-sm-8">
						<input class="form-control" name="title" placeholder="Title"
							required autofocus>
					</div>
				</div>
				<div class="col-sm-offset-2 form-group">
					<label for="inputDescription" class="col-sm-2 control-label">Description</label>
					<div class="col-sm-offset-2 col-sm-8">
						<input class="form-control" name="description"
							placeholder="Description" required="required">
					</div>
				</div>
				<div class="dropup">
					
					<label for="inputCategory" class="col-sm-2 control-label">Category</label>
					<div class="col-sm-offset-2 col-sm-8" >
					<select name="category">
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
				</div>
				<br/>
				<div>
					<label for="inputDeadLine" class="col-sm-2 control-label">Deadline</label>
					<div class="row">
						<div class="col-sm-offset-2 col-sm-8 btn-md">
							<div class="form-group">
								<div class='input-group date' id='datetimepicker1'>
									<input type='text' class="form-control" name="deadline" required="required"/> <span
										class="input-group-addon"> <span
										class="glyphicon glyphicon-calendar"></span>
									</span>
								</div>
							</div>
						</div>
						<script type="text/javascript">
							$(function() {
								$('#datetimepicker1').datetimepicker();
							});
						</script>
					</div>
				</div>


				<div class="col-sm-offset-2 form-group">
					<div class="col-sm-offset-2 col-sm-8">
						<button type="submit" class="btn btn-lg btn-success btn-block">Add</button>
					</div>
				</div>
				
				
			</div>
		</form>
	</div>
	
</body>
</html>


