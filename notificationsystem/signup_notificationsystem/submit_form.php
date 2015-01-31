<?php
	require 'configuration.php';
	$con = mysqli_connect($DB_server, $DB_user, $DB_pass, $DB_database);
	if (mysqli_connect_errno()) {
		printf("Connect failed: %s\n", mysqli_connect_error());
		exit();
	}
	$name=mysqli_real_escape_string($con,$_POST["name"]);
	$surname=mysqli_real_escape_string($con,$_POST["surname"]);
	$email=mysqli_real_escape_string($con,$_POST["email"]);
	$role=mysqli_real_escape_string($con,$_POST["role"]);
	$neigh=mysqli_real_escape_string($con,$_POST["neighbourhood"]);
	
	//echo $name." ".$surname." ".$email." ".$role." ".$neigh;
	
	$query="SELECT Granularity FROM $DB_roles_db WHERE ID = $role";
	$result=mysqli_query($con,$query);
	$record = mysqli_fetch_assoc($result);
	$gran = $record["Granularity"];
	
	if($gran!="Area"){
		$neigh=0;
}
	
	$query="INSERT INTO $DB_users_db (Name, Surname, eMail, ID_role, area) VALUES (\"$name\", \"$surname\", \"$email\", \"$role\", \"$neigh\")";
	//echo $query;
	mysqli_query($con, $query);
	$user_id = mysqli_insert_id($con);
	//echo "OK ID=$user_id";
	

?>

<!-- Follows a copy of signup_form.php, slightly modified. We should merge everything to a single page -->

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet" href="purecss/pure-min.css">
<link rel="stylesheet" href="purecss/grids-responsive-min.css">
<link rel="stylesheet" href="css/marketing.css">
<title> Sign up </title>
<script type="text/javascript" src="utility.js"></script>

<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
</head>

<body>

<div id="main">
	<!--
	<div class="splash-container">
		<div class="splash">
			<h1 class="splash-head">Municipality of Turin, Italy</h1>
			<p class="splash-subhead">
				Analysis of urban warnings as perceived by citizens
			</p>
			<p>
				<a href="http://purecss.io" class="pure-button pure-button-primary">Get Started</a>
			</p>
		</div>
	</div>
	-->
	
	<div class="banner">
    <h1 class="banner-head">
	Thanks, <?php echo $name ?>.<br></h1>
	</div>

<div class="content is-center">


        <h2 class="content-head is-center">User correctly registered with ID <?php echo $user_id ?></h2>

		<a class="pure-button" style="font-size:300%; margin-bottom:2em" href="signup_form.php"><i class="fa fa-pencil-square-o"></i>&nbsp Back to main page</a>
    

	<div class="pure-g">

		<div class="pure-u-1 pure-u-md-1-2 is-center"><img alt="File Icons" width="300" src="img/smartcity.jpg"></div>
	</div>
	<div class="pure-g">
		<div class="is-center l-box-lrg pure-u-1 pure-u-md-1-4">
			<img class="pure-img-responsive" alt="File Icons" width="300" src="img/logo_blu_alta.jpg">
		</div>
		
		<div class="is-center l-box-lrg pure-u-1 pure-u-md-1-4">
			<img class="pure-img-responsive" alt="File Icons" width="200" src="img/towl.jpg">
		</div>
		
		<div class="is-center l-box-lrg pure-u-1 pure-u-md-1-4">
			<img class="pure-img-responsive" alt="File Icons" width="300" src="img/telecom.jpg">
		</div>
		<div class="is-center l-box-lrg pure-u-1 pure-u-md-1-4">
			<img class="pure-img-responsive" alt="File Icons" width="300" src="img/filab.png">
		</div>
	</div>
			
			
			


	</div>
	

					
</div>
</body>

