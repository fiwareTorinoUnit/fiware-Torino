<label for="neighbourhood">Area </label>
<select name="neighbourhood" class="pure-input-1">


<?php
	require 'configuration.php';
	$con = mysqli_connect($DB_server, $DB_user, $DB_pass, $DB_database);
	if (mysqli_connect_errno()) {
		printf("Connect failed: %s\n", mysqli_connect_error());
		exit();
	}
	
	$dist=mysqli_real_escape_string($con,$_GET["district"]);

	

	$query="SELECT * FROM area_district WHERE id_District = $dist";



	$result_kpis = mysqli_query($con, $query);
	while ($record = mysqli_fetch_assoc($result_kpis)){
		$area=$record["Name"];
		$area_id=$record["id"];				
		echo '<option value="'.$area_id.'" >'.$area.'</option>';

	}

?>
</select>