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

</head>

<body onload="load_areas();">

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
	<br></h1>
	</div>

<div class="content">

		
 

        <div class="pure-g">
		    <div class="pure-u-1 pure-u-md-5-8">
				
				<h3 class="content-head is-center">Monitoring non-emergency calls in Torino Smart City</h3>
				
			</div>

			<div class="pure-u-1 pure-u-md-1-8 is-center"><img alt="File Icons" height="50px" src="img/smartcity.jpg"></div>
			
            <div class="l-box-lrg pure-u-1 pure-u-md-2-5">
                
				<form  class="pure-form pure-form-stacked" action="submit_form.php" method="post" onsubmit="return check_before_submit(name.value, surname.value, email.value);" >
					<fieldset>
					<div id="loginmsg"></div>
					
					<label for="name"> Name </label> <input type="text" name="name" placeholder="Your Name"required/>
					<label for="surname">Surname </label> <input type="text" name="surname" placeholder="Your Surname"required/>
					<label for="email">Email </label> <input type="email"  name="email" placeholder="someaddress@whatever.com"required/>
					<label for="role">Role </label> 
					<select id="role" name="role" class="pure-input-1" onchange="hide_neigh(this);">
					<!-- <option value="Major">Major</option> -->
					<?php
						require 'configuration.php';
						$con = mysqli_connect($DB_server, $DB_user, $DB_pass, $DB_database);
						if (mysqli_connect_errno()) {
							printf("Connect failed: %s\n", mysqli_connect_error());
							exit();
						}
						$query="SELECT * FROM $DB_roles_db";
						$result_roles = mysqli_query($con, $query);
						$first=true; 
						while ($record = mysqli_fetch_assoc($result_roles)){
							$role_id=$record["ID"];
							$role_desc=$record["Description"];
							$gran=$record["Granularity"];
							if($gran=="District" || $gran=="Area"){
								if ($role_desc[strlen($role_desc)-1]=="1"){
									//if district, print only once
									$role_desc=substr($role_desc,0,strlen($role_desc)-11);
									echo '<option value="'.$role_id.'" orig_value="'.$role_id.'" Granularity="'.$gran.'">'.$role_desc.'</option>';
								}
								
							} else echo '<option value="'.$role_id.'" orig_value="'.$role_id.'" Granularity="'.$gran.'">'.$role_desc.'</option>';
							
							
								
							
							/*check if we've to show area menu*/
							if($first){
								$first=false;
								$display_neigh=$record["Granularity"]=="Area";
								$display_dist=$record["Granularity"]=="District";
							}
						}
						//$display=($result_roles[0]["Granularity"]!="Area");
					?>
					</select>
					<div class="pure-g">
					<div id="dist" class="pure-u-1 pure-u-md-1-5"
						<?php if (!$display_dist){ echo 'style="display:none"'; } ?>
					>
					<label for="district">District </label> 
					<select id="district" name="district"  class="pure-input-1" onchange="load_areas();">
						<?php
						$i=0;
						while($i<10){
							$i++;
							echo "<option value=$i>$i</option>";
							
						}
						?>
					</select>
					</div>
					
					<div id="neighbourhood" class="pure-u-1 pure-u-md-3-5"
						<?php if (!$display_neigh){ echo 'style="display:none"'; } ?>
					>
					<label for="neighbourhood">Neighbourhood </label> 
					<select name="neighbourhood"  class="pure-input-1">
					<?php
						$query="SELECT id, Name FROM $DB_neigh_db ORDER BY id";
						$result = mysqli_query($con, $query);
						while ($record = mysqli_fetch_assoc($result)){
							$neigh_id=$record["id"];
							$neigh_desc=$record["Name"];
							echo '<option value="'.$neigh_id.'">'.$neigh_desc.'</option>';
						}
					?>
					</select>
					</div id="neighbourhood">
					</div >
					<div>
					<input type="submit" class="pure-button" name="submit" value="Signup" /></div>
					</fieldset>
					</form>
            </div>

            <div class="l-box-lrg pure-u-1 pure-u-md-3-5">

                <h4>			PERSONALIZED NOTIFICATION ON NON-EMERGENCY CALLS TREND ACCORDING TO USER ROLE</h4>
                <p>


					Signing up to the notification platform:
				</p>
				<p>
					Enter your contact details and institutional role to
					receive notifications tailored to your role
                </p>

                <h4>Contact Us</h4>
                <p>
                    For further information, please contact us.
                </p>
            </div>
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
